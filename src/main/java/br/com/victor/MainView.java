package br.com.victor;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    private final Button btnLogin;
    private final Button btnRegister;
    private Logger log = LoggerFactory.getLogger(getClass());

    public MainView(@Autowired MessageBean bean) {
        //login
        LoginOverlay loginOverlay = new LoginOverlay();
        loginOverlay.setTitle("Please log in");
        loginOverlay.addLoginListener(e -> {
            Notification.show(bean.getMessage());
            log.info(e.getUsername()+" "+e.getPassword());
        });

        //register
        LoginOverlay registerOverlay = new LoginOverlay();
        registerOverlay.setTitle("Use this form to register");
        registerOverlay.addLoginListener(e -> {
            log.info(e.getUsername()+" "+e.getPassword());
            registerOverlay.setOpened(false);
        });

        this.btnLogin = new Button("login", VaadinIcon.CHILD.create(), e -> loginOverlay.setOpened(true));
        this.btnRegister = new Button("register", VaadinIcon.CHILD.create(), e -> registerOverlay.setOpened(true));
        HorizontalLayout actions = new HorizontalLayout(btnLogin, btnRegister);
        add(actions);
    }

}
