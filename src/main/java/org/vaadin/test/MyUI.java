package org.vaadin.test;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        TreeTable tt = new TreeTable();
        tt.setWidth("300px");
        tt.setHeight("300px");
        layout.addComponent(tt);

        tt.addContainerProperty("component", Component.class, "");
        tt.addContainerProperty("type", String.class, "bar");

        Layout l = new HorizontalLayout();
        l.addComponent(new Label("bar"));
        l.addComponent(new Label("bar"));
        tt.addItem(new Object[] { l, "HorizontalLayout" }, 1);

        l = new VerticalLayout();
        l.addComponent(new Label("baz"));
        l.addComponent(new Label("baz"));
        tt.addItem(new Object[] { l, "VerticalLayout" }, 2);

        Label lbl = new Label("<b>foo</b><br/><i>bar</i>");
        lbl.setContentMode(Label.CONTENT_XHTML);
        tt.addItem(new Object[] { lbl, "Label" }, 3);
        Button button = new Button("Test");
        button.setDescription("Description");
        tt.addItem(new Object[] {button , "Button 1" }, 4);
        tt.setParent(4, 3);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
