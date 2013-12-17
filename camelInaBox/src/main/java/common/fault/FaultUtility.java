/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.fault;

import common.info.ServiceClientType;
import common.info.SystemInfo;

/**
 *
 * @author TG3
 */
public class FaultUtility {

    /**
     * getBusinessFaultException
     * 
     * @param id
     * @param faultIdentifier
     * @param technicalMessage
     * @param userFriendlyMessage
     * @param systemInfo
     * @return 
     */
    public common.fault.BusinessFault getBusinessFaultException(
            String id,
            String faultIdentifier,
            String technicalMessage,
            String userFriendlyMessage,
            SystemInfo systemInfo) {

        return new common.fault.BusinessFault(
                id,
                this.getBusinessFault(
                faultIdentifier,
                technicalMessage,
                systemInfo.getRequestIdentifier(),
                systemInfo.getSessionIdentifier(),
                userFriendlyMessage,
                systemInfo.getServiceClient()));

    }

    /**
     * getSystemFaultException
     * 
     * @param id
     * @param faultIdentifier
     * @param technicalMessage
     * @param userFriendlyMessage
     * @param systemInfo
     * @return 
     */
    public common.fault.SystemFault getSystemFaultException(
            String id,
            String faultIdentifier,
            String technicalMessage,
            String userFriendlyMessage,
            SystemInfo systemInfo) {

        return new common.fault.SystemFault(
                id,
                this.getSystemFault(
                faultIdentifier,
                technicalMessage,
                systemInfo.getRequestIdentifier(),
                systemInfo.getSessionIdentifier(),
                userFriendlyMessage,
                systemInfo.getServiceClient()));

    }

    /**
     * getBusinessFault
     *
     * @param id
     * @param faultIdentifier
     * @param technicalMessage
     * @param requestIdentifier
     * @param sessionIdentifier
     * @param userFriendlyMessage
     * @param serviceClientType
     * @return
     */
    public common.fault.BusinessFault getBusinessFaultException(
            String id,
            String faultIdentifier,
            String technicalMessage,
            String requestIdentifier,
            String sessionIdentifier,
            String userFriendlyMessage,
            ServiceClientType serviceClientType) {
        return new common.fault.BusinessFault(
                id,
                this.getBusinessFault(
                faultIdentifier,
                technicalMessage,
                requestIdentifier,
                sessionIdentifier,
                userFriendlyMessage,
                serviceClientType));
    }

    /**
     * getSystemFaultException
     *
     * @param id
     * @param faultIdentifier
     * @param technicalMessage
     * @param requestIdentifier
     * @param sessionIdentifier
     * @param userFriendlyMessage
     * @param serviceClientType
     * @return
     */
    public common.fault.SystemFault getSystemFaultException(
            String id,
            String faultIdentifier,
            String technicalMessage,
            String requestIdentifier,
            String sessionIdentifier,
            String userFriendlyMessage,
            ServiceClientType serviceClientType) {
        return new common.fault.SystemFault(
                id,
                getSystemFault(
                faultIdentifier,
                technicalMessage,
                requestIdentifier,
                sessionIdentifier,
                userFriendlyMessage,
                serviceClientType));
    }

    /**
     * getBusinessFault
     *
     * @param faultIdentifier
     * @param technicalMessage
     * @param requestIdentifier
     * @param sessionIdentifier
     * @param userFriendlyMessage
     * @param serviceClientType
     * @return
     */
    private common.fault.types.BusinessFault getBusinessFault(
            String faultIdentifier,
            String technicalMessage,
            String requestIdentifier,
            String sessionIdentifier,
            String userFriendlyMessage,
            ServiceClientType serviceClientType) {
        common.fault.types.BusinessFault _businessFault = new common.fault.types.BusinessFault();
        _businessFault.setFaultIdentifier(faultIdentifier);
        _businessFault.setTechnicalMessage(technicalMessage);
        _businessFault.setUserFriendlyMessage(userFriendlyMessage);

        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setRequestIdentifier(requestIdentifier);
        systemInfo.setServiceClient(serviceClientType);
        systemInfo.setSessionIdentifier(sessionIdentifier);
        _businessFault.setSystemInfo(systemInfo);
        return _businessFault;
    }

    /**
     * getSystemFault
     *
     * @param faultIdentifier
     * @param technicalMessage
     * @param requestIdentifier
     * @param sessionIdentifier
     * @param userFriendlyMessage
     * @param serviceClientType
     * @return
     */
    private common.fault.types.SystemFault getSystemFault(
            String faultIdentifier,
            String technicalMessage,
            String requestIdentifier,
            String sessionIdentifier,
            String userFriendlyMessage,
            ServiceClientType serviceClientType) {
        common.fault.types.SystemFault _systemFault =
                new common.fault.types.SystemFault();
        _systemFault.setFaultIdentifier(faultIdentifier);
        _systemFault.setTechnicalMessage(technicalMessage);
        _systemFault.setUserFriendlyMessage(userFriendlyMessage);

        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setRequestIdentifier(requestIdentifier);
        systemInfo.setServiceClient(serviceClientType);
        systemInfo.setSessionIdentifier(sessionIdentifier);
        _systemFault.setSystemInfo(systemInfo);
        return _systemFault;
    }
}
