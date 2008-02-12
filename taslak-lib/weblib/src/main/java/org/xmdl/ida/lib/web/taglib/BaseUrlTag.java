package org.xmdl.ida.lib.web.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * to define static urls with taglibary.
 * usage:
 * define static params in web.xlm
 * <context-param>
 * <param-name>PATH_icon</param-name>
 * <param-value>/images/common/icon/</param-value>
 * </context-param>
 * OR
 * <context-param>
 * <param-name>PATH_images</param-name>
 * <param-value>/static/images//</param-value>
 * </context-param>
 *
 * in jsp.
 * <ida:base-url context="icon" path="edit.gif" />
 * OR
 * <ida:base-url context="images" path="picture1.jpeg" />
 *
 * @author Kemal Dogan
 * @version $Revision: 0.1 $ $Date: 2007-10-29 17:44:48 $
 */


public class BaseUrlTag extends TagSupport {

    private static final long serialVersionUID = 2007102900010167095L;
    public final static String PATH = "PATH_";
    private String context;
    private String path;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    /**
     * Process the start of this tag.
     *
     * @return int status
     * @throws javax.servlet.jsp.JspException if a JSP exception has occurred
     * @see javax.servlet.jsp.tagext.Tag#doStartTag()
     */
    public int doStartTag() throws JspException {
        String staticPath = "";
        if (context != null) {
            staticPath = pageContext.getServletContext().getInitParameter(PATH + context);
        }

        StringBuffer sb = new StringBuffer();
        sb.append("<img src=\"");
        sb.append(staticPath);
        sb.append(path);
        sb.append("\">");

        try {
            pageContext.getOut().write(sb.toString());
        } catch (IOException io) {
            throw new JspException(io);
        }

        return super.doStartTag();
    }

    /**
     * Release aquired resources to enable tag reusage.
     *
     * @see javax.servlet.jsp.tagext.Tag#release()
     */
    public void release() {
        super.release();
    }

}
