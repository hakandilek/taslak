package org.xmdl.ida.lib.web.taglib.freemarker.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.util.ValueStack;

public class IDATaglibModels {
    private ValueStack stack;
    private HttpServletRequest req;
    private HttpServletResponse res;
    private MoneyModel money;

    public IDATaglibModels(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        this.stack = stack;
        this.req = req;
        this.res = res;
    }

    public MoneyModel getDatepicker() {
        if (this.money == null) {
            this.money = new MoneyModel(this.stack, this.req, this.res);
        }

        return this.money;
    }
}
