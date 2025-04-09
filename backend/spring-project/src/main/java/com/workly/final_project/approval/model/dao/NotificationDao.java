package com.workly.final_project.approval.model.dao;

import java.util.List;

import com.workly.final_project.approval.model.vo.Approval;
import com.workly.final_project.approval.model.vo.ApprovalLine;

public interface NotificationDao {

	List<ApprovalLine> getUnreadNotifications(int userNo);

	void markAsRead(int approvalNo, int userNo);

	void notifyNextApprover(int approvalNo, int i);

	List<Approval> getUnreadNotifications2(int userNo);

	void markAsRead2(int approvalNo, int userNo);

}
