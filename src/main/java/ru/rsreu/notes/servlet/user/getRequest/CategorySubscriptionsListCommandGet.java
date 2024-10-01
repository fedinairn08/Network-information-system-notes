package ru.rsreu.notes.servlet.user.getRequest;

import ru.rsreu.notes.constant.RequestAttribute;
import ru.rsreu.notes.entity.Category;
import ru.rsreu.notes.service.CategoryService;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.enums.Jsp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategorySubscriptionsListCommandGet extends FrontCommand {
    private CategoryService categoryService;

    public CategorySubscriptionsListCommandGet(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
        categoryService = ServiceFactory.getCategoryService();
    }

    @Override
    public void execute() throws IOException, ServletException {
        List<Category> categories = categoryService.findAllSubscriptionsByUser(user.getUserId());
        request.setAttribute(RequestAttribute.CATEGORIES, categories);
        forward(Jsp.CATEGORY_SUBSCRIPTIONS_LIST);
    }
}
