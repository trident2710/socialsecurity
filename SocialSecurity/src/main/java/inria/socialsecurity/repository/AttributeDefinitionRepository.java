/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inria.socialsecurity.repository;

import inria.socialsecurity.entity.attribute.AttributeDefinition;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 *
 * @author adychka
 */
public interface AttributeDefinitionRepository  extends GraphRepository<AttributeDefinition>{
    AttributeDefinition findByName(String name);
}
