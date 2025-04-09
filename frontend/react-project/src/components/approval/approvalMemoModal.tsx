import { useState } from "react";
import { useNavigate } from "react-router-dom";
export const ApprovalMemoModal = ({ onClose, onSave, approvalNo }) => {

  console.log("footer에서 받은 approvalNo값:", approvalNo);
  
  const [memoContent, setMemocontent] = useState("");
  const navigate = useNavigate();
  return (
    <div
      style={{
        position: "fixed",
        top: "50%",
        left: "50%",
        transform: "translate(-50%, -50%)",
        background: "rgba(0, 0, 0, 0.5)", // 반투명 배경 추가
        width: "100vw",
        height: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        zIndex: 1000
      }}
    >
      <div
        style={{
          width: "442px", // :흰색_확인_표시: 모달 크기 조정
          background: "white",
          borderRadius: "8px",
          border: "1px solid #ccc",
          display: "flex",
          flexDirection: "column",
          padding: "20px",
          position: "relative",
          boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.1)", // :흰색_확인_표시: 그림자 추가
        }}
      >
        {/* 타이틀 */}
        <div
          style={{
            fontSize: "18px",
            fontWeight: "700",
            marginBottom: "15px",
          }}
        >
          결재의견
        </div>
        {/* 내용 타이틀 */}
        <div
          style={{
            fontSize: "14px",
            fontWeight: "600",
            marginBottom: "5px",
          }}
        >
        </div>
        {/* 입력 필드 */}
        <textarea
          value={memoContent}
          onChange={(e) => setMemocontent(e.target.value)}
          placeholder="결재의견을 입력하세요"
          style={{
            width: "100%",
            height: "100px", // :흰색_확인_표시: 입력 필드 크기 조정
            borderRadius: "4px",
            border: "1px solid #ccc",
            padding: "10px",
            fontSize: "14px",
            resize: "none",
            overflowY: "auto",
            background: "#FAFAFA", // :흰색_확인_표시: 약간의 배경색 추가
          }}
        />
        {/* 버튼 */}
        <button
          style={{
            width: "100%", // :흰색_확인_표시: 버튼 크기 조정
            height: "40px",
            background: "#4880FF",
            borderRadius: "8px",
            border: "none",
            color: "white",
            fontSize: "16px",
            fontWeight: "600",
            cursor: "pointer",
            marginTop: "15px",
          }}
          onClick={() => {
            onSave(memoContent, approvalNo); // 부모 컴포넌트로 의견 전달
            navigate(`/ApprovalCompletePage/${approvalNo}`);
            setTimeout(() => {
              window.location.reload(); // 이동 후 새로고침
            });
          }}
        >
          저장
        </button>
      </div>
    </div>
  );
};