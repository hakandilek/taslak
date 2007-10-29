package org.xmdl.ida.lib.web.taglib;

import java.io.IOException;

import java.text.Collator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.naming.NamingException;

import org.xmdl.ida.lib.model.LabelValue;

import org.displaytag.tags.el.ExpressionEvaluator;

/**
 * Tag for creating multiple &lt;select&gt; options for displaying a list of
 * country names.
 * <p/>
 * <p/>
 * <b>NOTE</b> - This tag requires a Java2 (JDK 1.2 or later) platform.
 * </p>
 *
 * @author Jens Fischer, Matt Raible
 * @version $Revision: 1.4.2.1 $ $Date: 2006-06-10 08:00:48 -0600 (Sat, 10 Jun 2006) $
 */
public class BaseUrlTag extends TagSupport {

//    private static final long serialVersionUID = 3905528206810167095L;
    public final static String PATH_icon= "PATH_icon";
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
        ExpressionEvaluator eval = new ExpressionEvaluator(this, pageContext);
        if (context != null) {
            context = pageContext.getServletContext().getInitParameter(PATH_icon);
//            context = "http://localhost/static/" + context;
        } else
            context = "";


        StringBuffer sb = new StringBuffer();
        sb.append("<img src=\"");
        sb.append(context);
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

    /**
     * Build a List of LabelValues for all the available countries. Uses
     * the two letter uppercase ISO name of the country as the value and the
     * localized country name as the label.
     *
     * @param locale The Locale used to localize the country names.
     * @return List of LabelValues for all available countries.
     */
    @SuppressWarnings("unchecked")
            protected List<LabelValue> buildCountryList(Locale locale) {
        final Locale[] available = Locale.getAvailableLocales();

        List<LabelValue> countries = new ArrayList<LabelValue>();

        for (Locale anAvailable : available) {
            final String iso = anAvailable.getCountry();
            final String name = anAvailable.getDisplayCountry(locale);

            if (!"".equals(iso) && !"".equals(name)) {
                LabelValue country = new LabelValue(name, iso);

                if (!countries.contains(country)) {
                    countries.add(new LabelValue(name, iso));
                }
            }
        }

        Collections.sort(countries, new LabelValueComparator(locale));

        return countries;
    }

    /**
     * Class to compare LabelValues using their labels with
     * locale-sensitive behaviour.
     */
    @SuppressWarnings("unchecked")
            public class LabelValueComparator implements Comparator {
        private Comparator c;

        /**
         * Creates a new LabelValueComparator object.
         *
         * @param locale The Locale used for localized String comparison.
         */
        public LabelValueComparator(final Locale locale) {
            c = Collator.getInstance(locale);
        }

        /**
         * Compares the localized labels of two LabelValues.
         *
         * @param o1 The first LabelValue to compare.
         * @param o2 The second LabelValue to compare.
         * @return The value returned by comparing the localized labels.
         */
        @SuppressWarnings("unchecked")
                public final int compare(Object o1, Object o2) {
            LabelValue lhs = (LabelValue) o1;
            LabelValue rhs = (LabelValue) o2;

            return c.compare(lhs.getLabel(), rhs.getLabel());
        }
    }
}
