package controller.utils;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * JSP Custom tag
 * Return car price: one day price multiplied by the number of days.
 */
public class PriceTag extends TagSupport {
    private int price;
    private long days;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print(price * days);

        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
    }
}
