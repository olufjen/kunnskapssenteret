
package no.naks.services.nhn.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CitizenIdType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CitizenIdType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SSN"/>
 *     &lt;enumeration value="DN"/>
 *     &lt;enumeration value="HN"/>
 *     &lt;enumeration value="FHN"/>
 *     &lt;enumeration value="Invalid"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CitizenIdType", namespace = "http://register.nhn.no/Common")
@XmlEnum
public enum CitizenIdType {

    SSN("SSN"),
    DN("DN"),
    HN("HN"),
    FHN("FHN"),
    @XmlEnumValue("Invalid")
    INVALID("Invalid");
    private final String value;

    CitizenIdType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CitizenIdType fromValue(String v) {
        for (CitizenIdType c: CitizenIdType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
