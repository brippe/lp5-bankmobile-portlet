package edu.nocccd.portlets.lp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.portlet.*;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import com.sghe.luminis.core.spring.SpringContextUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContext;

import edu.nocccd.portlets.lp.config.AppConfig;
import edu.nocccd.portlets.lp.services.sso.AESUtil;

/**
 * BankMobile Portlet allows students to signin to BankMobile for refund disbursements  selections.
 * 
 * @author Brad Rippe
 * @version 1.0
 * @see http://bankmobiledisbursements.com/ for more information on BankMobile.
 */
@Component
public class BankMobilePortlet extends MVCPortlet {
	private static final Log log = LogFactoryUtil.getLog(BankMobilePortlet.class);
    private PortletContext ctx = null;
    private ApplicationContext appCtx;
    private PortletRequestDispatcher dispatcher;
    private AppConfig appConfig;
    private static final String MEMBER_VIEW = "/jsps/index.jsp";
    
    /**
     * @see javax.portlet.GenericPortlet#doView(RenderRequest request, RenderResponse response)
     */
    public void doView( RenderRequest request, RenderResponse response ) throws PortletException, IOException {

        StringBuffer username = new StringBuffer();
        username.append("@").append(getUserName());
        try {
            Calendar timestampCal = new GregorianCalendar();
            TimeZone utc = TimeZone.getTimeZone("UTC");
            SimpleDateFormat smp = new SimpleDateFormat("MM/dd/yyy HH:mm:ss");
            smp.setTimeZone(utc);
            String token = AESUtil.buildTokenString(appConfig.getClientCode(), username.toString(), smp.format(timestampCal.getTime()));
            String encrypted = AESUtil.aesEncrypt(appConfig.getSharedKey(), token);
            StringBuffer bmURL = new StringBuffer();
            bmURL.append(appConfig.getRefundURL()).append(encrypted).append("&clientcode=").append(appConfig.getClientCode());
            request.setAttribute("bankMobileURL", bmURL.toString());

        } catch (Exception e) {
            log.error(e);
        }
        dispatcher = ctx.getRequestDispatcher(MEMBER_VIEW);
        dispatcher.include( request, response );
    }

    /**
     * @see javax.portlet.GenericPortlet#init(PortletConfig config)
     */
    public void init( PortletConfig config ) throws PortletException {
        super.init( config );
        ctx = config.getPortletContext();
        appConfig = (AppConfig) SpringContextUtility.getBean("config");
    }

    /**
     * @see javax.portlet.GenericPortlet#destroy()
     */
    public void destroy() {
        dispatcher = null;
        super.destroy();
    }

    /**
     * Set application context
     * @param applicationContext
     */
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        appCtx = applicationContext;
    }

    /**
     * Get username
     * @return user's username
     */
    private String getUserName() {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = null;
        if ( null != context ) {
            Authentication auth = context.getAuthentication();
            if ( null != auth ) {
                username = auth.getName();
            }
        }
        return username;
    }
}