 package util;
 
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
 
public class MyFreemarkerView extends FreeMarkerView
 {
   protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request)
     throws Exception
   {
     model.put("base", request.getContextPath());
     super.exposeHelpers(model, request);
   }
 }


