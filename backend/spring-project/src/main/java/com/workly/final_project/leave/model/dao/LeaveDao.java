package com.workly.final_project.leave.model.dao;

import java.util.List;

import com.workly.final_project.common.model.vo.PageInfo;
import com.workly.final_project.leave.model.dto.AnnualHistoryDTO;
import com.workly.final_project.leave.model.vo.AnnualLeave;
import com.workly.final_project.leave.model.vo.LeavePolicy;

public interface LeaveDao {

	List<AnnualHistoryDTO> selectLeaveHistory(PageInfo pi, AnnualHistoryDTO history);

	int selectLeaveCount(AnnualHistoryDTO history);

	List<AnnualHistoryDTO> selectLeaveDetail(AnnualHistoryDTO userNo);

	int updateLeave(AnnualLeave leave);

	List<LeavePolicy> selectLeavePolicy();

	int updatePolicy(LeavePolicy policy);

	void insertAnnualLeave();
}
