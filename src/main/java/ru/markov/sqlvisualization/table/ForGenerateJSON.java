/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.markov.sqlvisualization.table;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodion
 */
public class ForGenerateJSON {

    private List<Value> value = new ArrayList<>();
    private List<Entity> entity = new ArrayList<>();
    private List<Attribute> attribute = new ArrayList<>();

    public List<Value> convertValue(List<Value> values) {
        for (Value value1 : values) {
            Attribute oldAttribute = value1.getAttribute();
            Entity oldEntity = oldAttribute.getEntity();
            Entity newEntity = new Entity(oldEntity.getIdEntity(), oldEntity.getNameEntity());
            Attribute newAttribute = new Attribute(oldAttribute.getIdAttribute(), oldAttribute.getNameAttribute(), newEntity);

            Value newValue = new Value(value1.getIdValue(), newAttribute, value1.getValue(), value1.getRowNumber());
            value.add(newValue);
        }

        return value;
    }

    public List<Entity> convertEntity(List<Entity> entitys) {
        for (Entity entity1 : entitys) {
            Entity newEntity = new Entity(entity1.getIdEntity(), entity1.getNameEntity());
            entity.add(newEntity);
        }
        return entity;
    }

    public List<Attribute> convertAttr(List<Attribute> attributes) {
        for (Attribute attribute1 : attributes) {
            Entity entity1 = new Entity(attribute1.getEntity().getIdEntity(), attribute1.getEntity().getNameEntity());
            Attribute newAttr = new Attribute(attribute1.getIdAttribute(), attribute1.getNameAttribute(), entity1);
            attribute.add(newAttr);
        }
        return attribute;
    }

    public List<Value> getValue() {
        return value;
    }

    public void setValue(List<Value> value) {
        this.value = value;
    }

}
