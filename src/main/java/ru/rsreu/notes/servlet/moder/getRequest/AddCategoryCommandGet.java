package ru.rsreu.notes.servlet.moder.getRequest;

import ru.rsreu.notes.constant.RequestAttribute;
import ru.rsreu.notes.constant.RequestConstants;
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

public class AddCategoryCommandGet extends FrontCommand {
    private CategoryService categoryService;

    public AddCategoryCommandGet(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
        categoryService = ServiceFactory.getCategoryService();
    }

    @Override
    public void execute() throws IOException, ServletException {
        String categoryIdString = request.getParameter(RequestConstants.CATEGORY_ID);
        if (categoryIdString != null) {
            Long categoryId = Long.valueOf(categoryIdString);
            Category category = categoryService.getCategory(categoryId);
            request.setAttribute(RequestAttribute.CATEGORY, category);
        }
        forward(Jsp.ADD_CATEGORY);
    }
}
