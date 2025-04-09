package com.workly.final_project.approval.controller;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workly.final_project.approval.model.service.ApprovalLineService;
import com.workly.final_project.approval.model.vo.ApprovalActualLine;
import com.workly.final_project.approval.model.vo.ApprovalFavoriteLine;
import com.workly.final_project.approval.model.vo.ApprovalLine;

@RestController
@RequestMapping("/api/approval")
@CrossOrigin(origins = "http://localhost:5173")
public class ApprovalLineController {
	
	@Autowired
	private ApprovalLineService service;

	// 결재라인 저장 API
	@PostMapping("saveApprovalLine")
	public ResponseEntity<String> saveApprovalLine(@RequestBody List<ApprovalLine> approvalLines){
		System.out.println("받아온 라인값:" + approvalLines);
		service.saveApprovalLine(approvalLines);
		return ResponseEntity.ok("결재라인이 저장완료");
	}
	
	// 즐겨찾기 정보 저장(LINE_NO자동 생성)
	@PostMapping("/saveFavoriteInfo")
	public ResponseEntity<Integer>saveFavoriteInfo(@RequestBody ApprovalFavoriteLine favoriteLineInfo){
        int lineNo = service.saveFavoriteInfo(favoriteLineInfo);
        return ResponseEntity.ok(lineNo);
		
	}
	
	// 즐겨찾기 결재라인 저장
	@PostMapping("/saveFavoriteLine")
	public ResponseEntity<String> saveFavoriteLine(@RequestBody List<ApprovalActualLine> approvalLines){
		System.out.println("받은 데이터 값:"+approvalLines);
		service.saveFavoriteLine(approvalLines);
		return ResponseEntity.ok("즐겨찾기 결재라인 저장 완료");
		
	}
	
	// 즐겨찾기 리스트 불러오기
	@GetMapping("/getFavoriteLines/{userNo}")
	public List<Map<String, Object>> getFavoriteLinesByUserNo(@PathVariable int userNo){
		return service.getFavoriteLinesByUserNo(userNo);
	}
	
	// 즐겨찾기 리스트 삭제
	 @DeleteMapping("/deleteFavoriteLine")
	 public ResponseEntity<String> deleteFavoriteLine(
			 @RequestParam("userNo") int userNo,
			 @RequestParam("favoriteName") String favoriteName){
		 
		 favoriteName = URLDecoder.decode(favoriteName, StandardCharsets.UTF_8);
		 
		 System.out.println("받아온 값:"+userNo + favoriteName);
		 try {
	        service.deleteFavoriteLine(userNo, favoriteName);
	        return ResponseEntity.ok("즐겨찾기 삭제 성공");
		 } catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("즐겨찾기 삭제 실패");
		 }
	 }
	 
	 // 예빈 추가
	 // 전자결재 승인 시 다음 사람에게 결재 토스
	 @PostMapping("/approve")
	 public ResponseEntity<?> approveDocument(@RequestBody ApprovalLine approvalLine){
		 boolean isSuccess = service.approveDocument(approvalLine);
		 
		 System.out.println("approvalLine 확인:" + approvalLine);
		 
		 if(isSuccess) {
			 return ResponseEntity.ok("승인 성공");
		 }else {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("승인 실패");
		 }
	 }
	 
	 // 현재 사용자가 마지막 결재자인지 확인하는 API
	 @GetMapping("/checkFinalApprover")
	 public ResponseEntity<Map<String, Integer>> checkFinalApprover(@RequestParam int approvalNo){
		 int isFinalApprover = service.isFinalApprover(approvalNo) ? 1 : 0;
		 Map<String, Integer> response = new HashMap<>();
		 response.put("isFinalApprover", isFinalApprover);
		 return ResponseEntity.ok(response);
	 }
	 
	 // 마지막 결재자인 경우 'APPROVAL' 테이블 상태 변경
	 @PostMapping("/updateFinalApproval")
	 public ResponseEntity<String> updateFinalApproval(@RequestBody Map<String, Integer> request){
		 int approvalNo = request.get("approvalNo");
		 int userNo = service.selectApprovalUserNo(approvalNo);
		 
		 service.updateFinalApproval(approvalNo);
		 
		 String type = service.selectApprovalType(approvalNo);
		 if(type.equals("휴가원")) {
			 service.updateAnnualLeave(userNo, approvalNo);
		 }
				 
		 return ResponseEntity.ok("결재 문서 최종 승인 완료");
	 }
	 // 예빈 추가 끝
}














