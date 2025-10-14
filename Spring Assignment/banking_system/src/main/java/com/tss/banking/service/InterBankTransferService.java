package com.tss.banking.service;

import java.util.List;
import java.util.Map;

import com.tss.banking.dto.request.InterBankTransferRequestDto;
import com.tss.banking.dto.response.InterBankTransferResponseDto;
import com.tss.banking.dto.request.TransferActionRequestDto;
import com.tss.banking.dto.response.TransferRequestSummaryDto;
import com.tss.banking.entity.User;

public interface InterBankTransferService {
    
    /**
     * Initiate inter-bank transfer request
     * @param accountId Source account ID
     * @param transferRequest Transfer request details
     * @param customerUser Authenticated customer user
     * @return Transfer response with reference number
     */
    InterBankTransferResponseDto initiateTransfer(Integer accountId, InterBankTransferRequestDto transferRequest, User customerUser);
    
    /**
     * Get all pending transfer requests for admin review
     * @param adminUser Authenticated admin user
     * @return List of pending transfer requests
     */
    List<TransferRequestSummaryDto> getPendingTransferRequests(User adminUser);
    
    /**
     * Approve a transfer request
     * @param requestId Transfer request ID
     * @param actionRequest Approval details
     * @param adminUser Authenticated admin user
     * @return Success response
     */
    String approveTransferRequest(Integer requestId, TransferActionRequestDto actionRequest, User adminUser);
    
    /**
     * Reject a transfer request
     * @param requestId Transfer request ID
     * @param actionRequest Rejection details
     * @param adminUser Authenticated admin user
     * @return Success response
     */
    String rejectTransferRequest(Integer requestId, TransferActionRequestDto actionRequest, User adminUser);
    
    /**
     * Get transfer requests initiated by this bank
     * @param customerUser Authenticated customer user
     * @return List of transfer requests
     */
    List<TransferRequestSummaryDto> getCustomerTransferRequests(User customerUser);
    
    /**
     * Get transfer request details by reference number
     * @param transferReference Transfer reference number
     * @param customerUser Authenticated customer user
     * @return Transfer request details
     */
    TransferRequestSummaryDto getTransferRequestByReference(String transferReference, User customerUser);
    
    /**
     * Process refund for rejected inter-bank transfer
     * @param refundRequest Refund request details
     * @return Success response
     */
    String processRefund(Map<String, Object> refundRequest);
}
