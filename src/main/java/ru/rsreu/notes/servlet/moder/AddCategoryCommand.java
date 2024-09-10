package ru.rsreu.notes.servlet.moder;

import ru.rsreu.notes.constant.RequestAttribute;
import ru.rsreu.notes.constant.RequestConstants;
import ru.rsreu.notes.entity.Category;
import ru.rsreu.notes.service.CategoryService;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.enums.Jsp;
import ru.rsreu.notes.utils.enums.Path;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCategoryCommand extends FrontCommand {
    private CategoryService categoryService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);
        categoryService = ServiceFactory.getCategoryService();
    }

    @Override
    public void process() throws ServletException, IOException {
        String categoryIdString = request.getParameter(RequestConstants.CATEGORY_ID);
        if (categoryIdString != null) {
            Long categoryId = Long.valueOf(categoryIdString);
            Category category = categoryService.getCategory(categoryId);
            request.setAttribute(RequestAttribute.CATEGORY, category);
        }
        forward(Jsp.ADD_CATEGORY);
    }

    @Override
    public void send() throws ServletException, IOException {
        Category category = null;
        try {
            category = this.mapCategory();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (category.getCategoryId() != null) {
            categoryService.updateCategory(category);
        } else {
            categoryService.save(category);
        }

        response.sendRedirect(Path.MODER_PROFILE.getAbsolutePath());
    }

    private Category mapCategory() {
        String id = request.getParameter("id");
        return new Category(
                id != null && !id.isEmpty() ? Long.valueOf(request.getParameter("id")) : null,
                request.getParameter(RequestAttribute.CATEGORY)
        );
    }
}
