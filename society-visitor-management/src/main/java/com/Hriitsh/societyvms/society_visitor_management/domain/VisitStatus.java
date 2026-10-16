package com.Hriitsh.societyvms.society_visitor_management.domain;
public enum VisitStatus {
    PENDING,
    APPROVED,
    CHECKED_IN,
    COMPLETED,
    EXPIRED;

    public boolean canTransitionTo(VisitStatus next){
        switch(this){
            case PENDING:
                return next == APPROVED || next == EXPIRED;
            
            case APPROVED:
                return next == CHECKED_IN || next == EXPIRED;
            
            case CHECKED_IN:
                return next == COMPLETED;
            
            case COMPLETED:
                return false;
            case EXPIRED:
                return false;
            default:
                return false;
        }
    }
}
