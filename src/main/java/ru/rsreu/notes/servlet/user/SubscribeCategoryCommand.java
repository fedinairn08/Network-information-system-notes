package ru.rsreu.notes.servlet.user;

import ru.rsreu.notes.constant.RequestConstants;
import ru.rsreu.notes.service.CategoryService;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.enums.Jsp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubscribeCategoryCommand extends FrontCommand {
    private CategoryService categoryService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);
        categoryService = ServiceFactory.getCategoryService();
    }

    @Override
    public void send() throws ServletException, IOException {
        Long categoryId = Long.valueOf(request.getParameter(RequestConstants.CATEGORY_ID));
        categoryService.subscribeToCategory(user.getUserId(), categoryId);
        forward(Jsp.CATEGORY_SUBSCRIPTIONS_LIST);
    }
}
