package ru.rsreu.notes.filters;

import ru.rsreu.notes.constant.ContentType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ContentTypeFilter implements Filter {
    private static final String ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        String uri = ((HttpServletRequest)request).getRequestURI();

        response.setContentType(ContentType.DEFAULT);

        for (String fileType: ContentType.getFileTypes()) {
            if (uri.contains(fileType)) {
                String mimeType = ContentType.getContentType(fileType);

                response.setContentType(mimeType);
                break;
            }
        }

        response.setCharacterEncoding(ENCODING);
        request.setCharacterEncoding(ENCODING);

        next.doFilter(request, response);
    }
}
