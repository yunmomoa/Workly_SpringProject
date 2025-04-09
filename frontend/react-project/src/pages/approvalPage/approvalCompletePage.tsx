import ApprovalCompleteDocument from "../../components/approval/approvalCompleteDocument";
import { ApprovalCompleteHeader } from "../../components/approval/approvalCompleteHeader";
import ApprovalCompleteReply from "../../components/approval/approvalCompleteRply";

import Header from "../../components/common/Header";
import Sidebar from "../../components/common/Sidebar";
import { ApprovalCompleteFooter } from "../../components/approval/approvalCompleteFooter";

export const ApprovalCompletePage = () => {

  return (
    <div className="mainpageContainer">
      <Sidebar />
      <div className="componentContainer">
        <Header />
        <div style={scrollableContentStyle}>
          <ApprovalCompleteHeader />                         
          <ApprovalCompleteDocument />
          <ApprovalCompleteReply />
          <ApprovalCompleteFooter/>
        </div>
      </div>
    </div>
  );
};

// ✅ **스타일 정의 (TSX 내부에서 적용)**
const scrollableContentStyle = {
  overflowY: "auto", // ✅ 세로 스크롤바 적용
  maxHeight: "calc(100vh - 100px)", // ✅ 화면 높이에서 일부 여백 제외 (조정 가능)
  paddingRight: "10px", // ✅ 스크롤바 공간 확보
};

export default ApprovalCompletePage;
