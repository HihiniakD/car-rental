package controller.utils;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;


public class DateInFutureTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print(LocalDate.now().plusDays(3+ (int) (Math.random()*4)));

        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

}
