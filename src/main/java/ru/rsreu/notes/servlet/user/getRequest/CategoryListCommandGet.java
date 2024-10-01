package ru.rsreu.notes.servlet.user.getRequest;

import ru.rsreu.notes.constant.RequestAttribute;
import ru.rsreu.notes.entity.Category;
import ru.rsreu.notes.service.CategoryService;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.UserHelper;
import ru.rsreu.notes.utils.enums.Jsp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CategoryListCommandGet extends FrontCommand {
    private CategoryService categoryService;

    public CategoryListCommandGet(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
        categoryService = ServiceFactory.getCategoryService();
    }

    @Override
    public void execute() throws IOException, ServletException {
        List<Category> categories = categoryService.findAll();
        Optional<Long> userId = UserHelper.getUserFromCookies(request.getCookies());

        Map<Category, Boolean> categoriesWithStatus = categoryService.getCategoriesWithSubscriptionStatus(userId.get());
        request.setAttribute("categoriesWithStatus", categoriesWithStatus);

        request.setAttribute(RequestAttribute.CATEGORIES, categories);
        forward(Jsp.CATEGORY_LIST);
    }
}
