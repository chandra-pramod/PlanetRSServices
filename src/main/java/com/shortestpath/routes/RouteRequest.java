//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.09.13 at 03:47:37 AM CAT 
//


package com.shortestpath.routes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="planetOrigin" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="planetDestination" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "planetOrigin",
    "planetDestination"
})
@XmlRootElement(name = "RouteRequest")
public class RouteRequest {

    @XmlElement(required = true)
    protected String planetOrigin;
    @XmlElement(required = true)
    protected String planetDestination;

    /**
     * Gets the value of the planetOrigin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanetOrigin() {
        return planetOrigin;
    }

    /**
     * Sets the value of the planetOrigin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanetOrigin(String value) {
        this.planetOrigin = value;
    }

    /**
     * Gets the value of the planetDestination property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanetDestination() {
        return planetDestination;
    }

    /**
     * Sets the value of the planetDestination property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanetDestination(String value) {
        this.planetDestination = value;
    }

}
