# Audit

The [security audit endpoint](http://localhost:8080/actuator/auditevents) has been activated. This allows registering, and logging, authentication failures in more detail.

All the configuration, apart from activating the actuator, is kept in AuditConfig. With `AuditEventLogger` in particular used to log the details of any event. Mainly to know why the requests failed.