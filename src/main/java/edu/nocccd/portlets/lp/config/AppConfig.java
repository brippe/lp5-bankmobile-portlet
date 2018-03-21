package edu.nocccd.portlets.lp.config;

import org.springframework.stereotype.Component;

/**
 * Portlet configuration
 *
 * @author Brad Rippe
 * @version 1.0
 */
@Component
public class AppConfig {

    private String clientCode;
    private String sharedKey;
    private String refundURL;

    /**
     *
     * @param clientCode bank mobile client code
     * @param sharedKey bank mobile sharedKey
     * @param refundURL bank mobile refund url
     */
    AppConfig(String clientCode, String sharedKey, String refundURL) {
        this.clientCode = clientCode;
        this.sharedKey = sharedKey;
        this.refundURL = refundURL;
    }

    /**
     * Get client code
     * @return
     */
    public String getClientCode() {
        return clientCode;
    }

    /**
     * Set Client code
     * @param clientCode
     */
    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    /**
     * Get shared key
     * @return
     */
    public String getSharedKey() {
        return sharedKey;
    }

    /**
     * Set shared key
     * @param sharedKey
     */
    public void setSharedKey(String sharedKey) {
        this.sharedKey = sharedKey;
    }

    /**
     * Get refund url
     * @return
     */
    public String getRefundURL() {
        return refundURL;
    }

    /**
     * Set refund url
     * @param refundURL
     */
    public void setRefundURL(String refundURL) {
        this.refundURL = refundURL;
    }
}