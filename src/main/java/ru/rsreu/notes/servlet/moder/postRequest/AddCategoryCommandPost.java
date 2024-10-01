package ru.rsreu.notes.servlet.moder.postRequest;

import ru.rsreu.notes.constant.RequestAttribute;
import ru.rsreu.notes.entity.Category;
import ru.rsreu.notes.service.CategoryService;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.enums.Path;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCategoryCommandPost extends FrontCommand {
    private CategoryService categoryService;

    public AddCategoryCommandPost(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
        categoryService = ServiceFactory.getCategoryService();
    }

    @Override
    public void execute() throws IOException, ServletException {
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
