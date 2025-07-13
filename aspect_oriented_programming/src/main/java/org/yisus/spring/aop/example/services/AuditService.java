package org.yisus.spring.aop.example.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.yisus.spring.aop.example.models.AuditLog;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuditService {
    private List<AuditLog> auditLogs = new ArrayList<AuditLog>();
    Logger logger = LoggerFactory.getLogger(AuditService.class);


    public void audit(AuditLog auditLog) {
        logger.warn("\033[31m Se ha detectado una acción importante: " + auditLog);
        auditLogs.add(auditLog);
    }


    public List<AuditLog> getAuditLogs() {
        return auditLogs;
    }

    public void printAuditLogs() {
        logger.info("\033[31m Audit Logs:");
        auditLogs.forEach(auditLog -> logger.info("\033[31m"+auditLog.toString()));
    }
}
