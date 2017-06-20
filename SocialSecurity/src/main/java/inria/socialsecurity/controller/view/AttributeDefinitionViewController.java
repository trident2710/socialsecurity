/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inria.socialsecurity.controller.view;

import static inria.socialsecurity.constants.param.AttributeDefinition.*;
import inria.socialsecurity.exception.ObjectNotFoundException;
import inria.socialsecurity.exception.WrongArgumentException;
import inria.socialsecurity.model.AttributeDefinitionModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * controller bean containig the response logic for the attributes connects the
 * jsp views with the requests
 *
 * @see Attribute
 * @author adychka
 */
@Controller
@RequestMapping("/attributes/**")
public class AttributeDefinitionViewController {


    @Autowired
    AttributeDefinitionModel adm;

    //get the page listing all attributes
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String getAllAttributesPage(Model model) {
        model.addAttribute(PRIMITIVE_ATTRIBUTES, adm.getPrimitiveAttributeDefinitions()); //add the primitive attributes to view
        model.addAttribute(COMPLEX_ATTRIBUTES, adm.getComlexAttributeDefinitions()); //add the complex attributes to view
        return "attribute/attribute_all";
    }

    /**
     * @todo: update with primitive attribute
     * get the update attribute page for the attribute with the passed id
     *
     * @param id - attribute id
     * @param model - view model
     * @return jsp page name for updating attribute
     * @throws WrongArgumentException if the attribute with such id doesn't
     * exist or belongs to other class
     * @throws ObjectNotFoundException if the id argument is malformed
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String getAttributeUpdatePage(@PathVariable String id, Model model) throws WrongArgumentException, ObjectNotFoundException {
        Long idValue;
        try {
            idValue = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new WrongArgumentException();
        }
        
        model.addAttribute(ATTRIBUTE, adm.getComplexAttributeDefinitionById(idValue));
        model.addAttribute(PRIMITIVE_ATTRIBUTES, adm.getPrimitiveAttributeDefinitions());
        return "attribute/complex_attribute_update";
 
    }

    /**
     * @todo: update with primitive attribute
     * get the page for adding new complex attribute
     *
     * @param model
     * @return the jsp page name for adding the attribute
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String getAttributeAddPage(Model model) {
        model.addAttribute(PRIMITIVE_ATTRIBUTES, adm.getPrimitiveAttributeDefinitions()); //pass the primitive attributes to view
        return "attribute/complex_attribute_add";
    }
    
    /**
     * process form data post from the page
     * @param request
     * @return 
     * @throws inria.socialsecurity.exception.WrongArgumentException 
     * @throws inria.socialsecurity.exception.ObjectNotFoundException 
     */
    @RequestMapping(value = "form-post", method = RequestMethod.POST)
    public String processFormPost(HttpServletRequest request) throws WrongArgumentException, ObjectNotFoundException{
        if(request.getParameter(ID)!=null){ 
            Long idVal;
            try {
                idVal = Long.parseLong(request.getParameter(ID));
            } catch (NumberFormatException e) {
                throw new WrongArgumentException();
            }
            adm.updateAttributeDefinitionFromHttpRequest(idVal, request);
        } else
            adm.createAttributeDefinitionFromHttpRequest(request);
        return "redirect:attributes/all";
    }

   
}
