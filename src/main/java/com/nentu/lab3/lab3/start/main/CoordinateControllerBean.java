package com.nentu.lab3.lab3.start.main;


import com.nentu.lab3.lab3.start.storage.db.DBStorage;
import com.nentu.lab3.lab3.start.storage.interfaces.IStorage;
import com.nentu.lab3.lab3.start.utils.ValidationDataStorage;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ApplicationScoped
public class CoordinateControllerBean extends ValidationDataStorage {
    private CoordinateBean currentCoordinateBean = new CoordinateBean();

    private IStorage<CoordinateBean> storage = new DBStorage();

    private Map<String, String> validationErrorMsg = new HashMap<>() {{
        put("x", "Choose X coordinate");
        put("y", "Please, set Y");
        put("r", "");
    }};

    public List<CoordinateBean> getCoordinateList() {
        return storage.getReversedList();
    }

    public void graphClickListener() {
        var params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (!(params.containsKey("x") && params.containsKey("y")))
            return;
        var r = currentCoordinateBean.getR();
        currentCoordinateBean.setX(round(Float.parseFloat(params.get("x")) * r));
        currentCoordinateBean.setY(round(Float.parseFloat(params.get("y")) * r));
        checkCoordinate();
    }

    private float round(Float f) {
        var t = Math.pow(10, 3);
        return (float) (Math.round(f * t) / t);
    }

    public CoordinateBean getCurrentCoordinate() {
        return currentCoordinateBean;
    }

    public String getYValidationError() {
        return validationErrorMsg.get("y");
    }

    public String getXStyle() {
        if (new CoordinateManagerBean().validateX(currentCoordinateBean).success)
            return "";
        return "background-color: bisque";
    }

    public String getXValidationError() {
        validationErrorMsg.put(
                "x",
                new CoordinateManagerBean().validateX(currentCoordinateBean).msg
        );
        return validationErrorMsg.get("x");
    }

    public void changeYCoordinateListen(ValueChangeEvent event) {
        String value = (String) event.getNewValue();
        try {
            Float newValue = Float.parseFloat(value);
            if (newValue < getYMin() || newValue > getYMax()) {
                validationErrorMsg.put("y", "Y must be in (" + getYMin() + ", " + getYMax() + ")");
                currentCoordinateBean.setY(Float.NEGATIVE_INFINITY);
                return;
            }
            validationErrorMsg.put("y", "");
            currentCoordinateBean.setY(newValue);

        } catch (NumberFormatException e) {
            validationErrorMsg.put("y", "Y must be float");
            currentCoordinateBean.setY(Float.NEGATIVE_INFINITY);
        }
    }

    public void clearBD() {
        storage.clear();
    }

    public void checkCoordinate() {
        if (new CoordinateManagerBean().isInvalid(currentCoordinateBean)) {
            PrimeFaces.current().executeScript("alert ('" + validationErrorMsg.get("y") + "');");
            return;
        }
        new CoordinateManagerBean().check(currentCoordinateBean);
        storage.add(currentCoordinateBean);
        var oldR = currentCoordinateBean.getR();
        currentCoordinateBean = new CoordinateBean();
        currentCoordinateBean.setR(oldR);

        validationErrorMsg.put("y", "Please, set Y");
        PrimeFaces.current().executeScript("displayGraph(); drawLastPoint();");

    }
}
