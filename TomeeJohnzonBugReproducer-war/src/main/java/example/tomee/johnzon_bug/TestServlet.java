package example.tomee.johnzon_bug;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;

@WebServlet("/TriggerTest")
public class TestServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest p_req, HttpServletResponse p_resp) throws ServletException, IOException {
    
    // Trigger an outgoing JAX-RS call with a custom CXF context.
    // This will generate a memory leak via Johnzon's CDI integration, unless TomEEJsonbProvider is patched
    // to set johnzon.cdi.activated = Boolean.FALSE
    
    JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
    bean.setServiceClass(IJaxrsWebservice.class);
    bean.setAddress("http://127.0.0.1:8080/reproducer");
    
    final IJaxrsWebservice proxy = bean.create(IJaxrsWebservice.class);
    
    ModelObject model = new ModelObject();
    model.setFoo("foo");
    model.setBar("bar");
    proxy.trigger(model);
    
    p_resp.setStatus(HttpServletResponse.SC_OK);
  }
}
