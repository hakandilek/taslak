package org.xmdl.ida.lib.web.taglib.freemarker.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.freemarker.tags.TagModel;
import org.xmdl.ida.lib.web.taglib.component.MoneyTagBean;

import com.opensymphony.xwork2.util.ValueStack;

public class MoneyModel extends TagModel {

    public MoneyModel(ValueStack stack, HttpServletRequest req,
        HttpServletResponse res) {
        super(stack, req, res);
    }

    @Override
    protected Component getBean() {
        return new MoneyTagBean(this.stack, this.req, this.res);
    }

}
