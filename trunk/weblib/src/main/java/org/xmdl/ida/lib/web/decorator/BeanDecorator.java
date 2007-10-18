package org.xmdl.ida.lib.web.decorator;

import org.displaytag.decorator.TableDecorator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanDecorator extends TableDecorator {

    public BeanDecorator() {
        super();
    }

    public String getDeleteCheckbox() {
        return "<input type=\"checkbox\" name=\"deleteId\" value=\"" + getId()+ "\"/>";
    }

    public String getId(){
        String id = "";

        Object obj = this.getCurrentRowObject();
        Class<?> theClass = obj.getClass();
        try {
            Method theMethod = theClass.getMethod("getId"); //all model classes must use getId for this to work
            id = String.valueOf(theMethod.invoke(obj));
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return id;
    }
}
