/**
 * Copyright 2012 Intellectual Reserve, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gedcomx.conversion;

import org.gedcomx.conclusion.ConclusionModel;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.conversion.gedcom.dq55.CommonMapper;
import org.gedcomx.fileformat.GedcomxOutputStream;
import org.gedcomx.metadata.foaf.Organization;
import org.gedcomx.metadata.rdf.Description;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class GedcomxOutputstreamConversionResult implements GedcomxConversionResult {
  private GedcomxOutputStream gedxOutputStream;

  public GedcomxOutputstreamConversionResult(OutputStream outputStream) throws IOException {
    gedxOutputStream = new GedcomxOutputStream(outputStream);
  }

  public void finish(boolean closeStream) throws IOException {
    if (closeStream)
      close();
  }

  public void close() throws IOException {
    gedxOutputStream.close();
  }

  @Override
  public void addPerson(Person person, Date lastModified) throws IOException {
    String entryName = CommonMapper.getPersonEntryName(person.getId());
    gedxOutputStream.addResource(ConclusionModel.GEDCOMX_CONCLUSION_V1_XML_MEDIA_TYPE, entryName, person, lastModified);
  }

  @Override
  public void addRelationship(Relationship relationship, Date lastModified) throws IOException {
    String entryName = CommonMapper.getRelationshipEntryName(relationship.getId());
    gedxOutputStream.addResource(ConclusionModel.GEDCOMX_CONCLUSION_V1_XML_MEDIA_TYPE, entryName, relationship, lastModified);
  }

  @Override
  public void addDescription(Description description, Date lastModified) throws IOException {
    String entryName = CommonMapper.getDescriptionEntryName(description.getId());
    gedxOutputStream.addResource(ConclusionModel.GEDCOMX_CONCLUSION_V1_XML_MEDIA_TYPE, entryName, description, lastModified);
  }

  @Override
  public void setDatasetContributor(org.gedcomx.metadata.foaf.Person person, Date lastModified) throws IOException {
    String entryName = CommonMapper.getContributorEntryName(person.getId());
    Map<String, String> attributes = new HashMap<String, String>();
    attributes.put("DC-creator", Boolean.TRUE.toString());
    gedxOutputStream.addResource(ConclusionModel.GEDCOMX_CONCLUSION_V1_XML_MEDIA_TYPE, entryName, person, lastModified, attributes);
  }

  @Override
  public void addOrganization(Organization organization, Date lastModified) throws IOException {
    String entryName = CommonMapper.getOrganizationEntryName(organization.getId());
    gedxOutputStream.addResource(ConclusionModel.GEDCOMX_CONCLUSION_V1_XML_MEDIA_TYPE, entryName, organization, lastModified);
  }

/*
  public void write(OutputStream outputStream) throws IOException {
    GedcomxOutputStream gedxOutputStream = new GedcomxOutputStream(outputStream);
    try {
      // Persons
      for (Person person : getPersons()) {
        String entryName = CommonMapper.getPersonEntryName(person.getId());
        gedxOutputStream.addResource(ConclusionModel.GEDCOMX_CONCLUSION_V1_XML_MEDIA_TYPE
          , entryName
          , person);
      }

      // Source Descriptions
      for (Description description : getDescriptions()) {
      }
    } finally {
      gedxOutputStream.close();
    }
  }
*/

}
