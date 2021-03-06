/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inria.socialsecurity.repository;

import inria.socialsecurity.entity.harmtree.HarmTreeElement;
import inria.socialsecurity.entity.harmtree.HarmTreeVertex;
import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/*
 * interface defining the data accessing repository for the objects of type HarmTreeElement and it's subclasses
 * @see HarmTreeElement
 * @author adychka
 */
public interface HarmTreeRepository extends GraphRepository<HarmTreeElement> {

    /**
     * get the vertices of the harm tree
     *
     * @see HarmTreeVertex
     * @return list of harm tree vertices
     */
    @Transactional
    @Query("match (a:HarmTreeVertex)  return a")
    List<HarmTreeVertex> getTreeVertices();

    /**
     * delete the harm tree element by id with it's relationships note: the
     * related nodes will not be deleted
     *
     * @param id
     */
    @Transactional
    @Query("match (n) where id(n)={id} detach delete n")
    void detachDelete(@Param("id") Long id);
    
    
    
}
