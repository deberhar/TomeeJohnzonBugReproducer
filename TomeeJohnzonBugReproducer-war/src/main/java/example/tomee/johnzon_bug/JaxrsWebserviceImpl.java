package example.tomee.johnzon_bug;


public class JaxrsWebserviceImpl implements IJaxrsWebservice {
  @Override
  public ModelObject trigger(ModelObject p_param) {
    return p_param;
  }

}
