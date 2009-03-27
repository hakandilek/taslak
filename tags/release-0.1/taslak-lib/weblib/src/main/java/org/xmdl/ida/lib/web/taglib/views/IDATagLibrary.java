package org.xmdl.ida.lib.web.taglib.views;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.TagLibrary;
import org.xmdl.ida.lib.web.taglib.freemarker.tags.IDATaglibModels;

import com.opensymphony.xwork2.util.ValueStack;

public class IDATagLibrary implements TagLibrary {

    public Object getFreemarkerModels(ValueStack stack, HttpServletRequest req,
        HttpServletResponse res) {
        return new IDATaglibModels(stack, req, res);
    }

    @SuppressWarnings("unchecked")
    public List<Class> getVelocityDirectiveClasses() {
        Class[] directives = new Class[] {  };
        return Arrays.asList(directives);
    }

}
