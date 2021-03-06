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
package org.gedcomx.conversion.gedcom.dq55;

import org.folg.gedcom.model.Family;
import org.folg.gedcom.model.Gedcom;
import org.folg.gedcom.parser.ModelParser;
import org.gedcomx.conclusion.Fact;
import org.gedcomx.conclusion.Person;
import org.gedcomx.conclusion.Relationship;
import org.gedcomx.conclusion.SourceReference;
import org.gedcomx.metadata.dc.DublinCoreDescriptionDecorator;
import org.gedcomx.metadata.rdf.Description;
import org.gedcomx.types.ConfidenceLevel;
import org.gedcomx.types.RelationshipType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;

import static org.testng.Assert.*;


public class SourceReferenceMapperTest {
  private DateFormat dateFormatter = DateFormat.getDateTimeInstance();
  private Gedcom gedcom;

  @BeforeClass
  public void setUp() throws Exception {
    SequentialIdentifierGenerator.reset();
    URL gedcomUrl = this.getClass().getClassLoader().getResource("Case007-SourceCitations.ged");
    File gedcomFile = new File(gedcomUrl.toURI());
    ModelParser modelParser = new ModelParser();

    gedcom = modelParser.parseGedcom(gedcomFile);
    assertNotNull(gedcom);
    assertNotNull(gedcom.getPeople());
    assertEquals(gedcom.getPeople().size(), 5);
    assertNotNull(gedcom.getFamilies());
    assertEquals(gedcom.getFamilies().size(), 2);
    assertNotNull(gedcom.getSources());
    assertEquals(gedcom.getSources().size(), 1);
    assertNotNull(gedcom.getRepositories());
    assertEquals(gedcom.getRepositories().size(), 1);
  }

  @Test
  public void testToSourcesAndSourceReferences1() throws Exception {
    Family dqFamily = gedcom.getFamilies().get(0);
    TestConversionResult result = new TestConversionResult();
    GedcomMapper gedcomMapper = new GedcomMapper();

    FamilyMapper mapper = new FamilyMapper();

    String generatedId = null;

    mapper.toRelationship(dqFamily, null, result);
    assertNotNull(result.getRelationships());
    assertEquals(result.getRelationships().size(), 3);
    for (Relationship gedxRelationship : result.getRelationships()) {
      if (gedxRelationship.getKnownType() == RelationshipType.Couple) {
        assertNotNull(gedxRelationship.getFacts());
        assertEquals(gedxRelationship.getFacts().size(), 1);
        Fact gedxFact = gedxRelationship.getFacts().get(0);
        assertNotNull(gedxFact);
        assertNotNull(gedxFact.getSources());
        assertEquals(gedxFact.getSources().size(), 1);
        SourceReference gedxSourceReference = gedxFact.getSources().get(0);
        assertNotNull(gedxSourceReference);
        assertNotNull(gedxSourceReference.getDescription());
        assertTrue(gedxSourceReference.getDescription().getResource().toString().startsWith("descriptions/"));
        generatedId = gedxSourceReference.getDescription().getResource().toString().substring("descriptions/".length());
        assertNotNull(generatedId, "1");
        assertNull(gedxSourceReference.getDescription().getExtensionAttributes());
        assertNull(gedxSourceReference.getDescription().getExtensionElements());
        assertNotNull(gedxSourceReference.getAttribution());
        assertEquals(gedxSourceReference.getAttribution().getKnownConfidenceLevel(), ConfidenceLevel.Certainly);
        assertNull(gedxSourceReference.getAttribution().getModified());
        assertNull(gedxSourceReference.getAttribution().getProofStatement());
        assertNull(gedxSourceReference.getAttribution().getContributor());
        assertNull(gedxSourceReference.getId());
        assertNull(gedxSourceReference.getType());
        assertNull(gedxSourceReference.getExtensionAttributes());
        assertNull(gedxSourceReference.getExtensionElements());
        assertNull(gedxSourceReference.getResource());
      }
    }
    assertNotNull(result.getDescriptions());
    assertEquals(result.getDescriptions().size(), 1);
    Description gedxSourceDescription = result.getDescriptions().get(0);
    assertNotNull(gedxSourceDescription);
    assertEquals(gedxSourceDescription.getId(), generatedId);
    assertNull(gedxSourceDescription.getType());
    assertNull(gedxSourceDescription.getExtensionAttributes());
    assertNull(gedxSourceDescription.getAbout());
    assertNotNull(gedxSourceDescription.getExtensionElements());
    DublinCoreDescriptionDecorator gedxDecoratedSourceDescription = DublinCoreDescriptionDecorator.newInstance(gedxSourceDescription);
    assertUnusedFieldsAreUnused(gedxDecoratedSourceDescription);
    assertEquals(gedxDecoratedSourceDescription.getCreated().size(), 1);
    assertEquals(gedxDecoratedSourceDescription.getCreated().get(0).getValue(), "31 Jan 1820");
    assertEquals(gedxDecoratedSourceDescription.getDescription().size(), 1);
    assertEquals(gedxDecoratedSourceDescription.getDescription().get(0).getValue(), "FHL INTL Film 1226427, Geboorten, 1820, No. 143");
    assertEquals(gedxDecoratedSourceDescription.getIsPartOf().size(), 1);
    assertEquals(gedxDecoratedSourceDescription.getIsPartOf().get(0).getValue(), "descriptions/SOUR1");
  }

  @Test
  public void testToSourcesAndSourceReferences2() throws Exception {
    Family dqFamily = gedcom.getFamilies().get(1);
    TestConversionResult result = new TestConversionResult();
    GedcomMapper gedcomMapper = new GedcomMapper();

    FamilyMapper mapper = new FamilyMapper();

    String generatedId = null;

    mapper.toRelationship(dqFamily, null, result);
    assertNotNull(result.getRelationships());
    assertEquals(result.getRelationships().size(), 3);
    for (Relationship gedxRelationship : result.getRelationships()) {
      if (gedxRelationship.getKnownType() == RelationshipType.Couple) {
        assertNotNull(gedxRelationship.getFacts());
        assertEquals(gedxRelationship.getFacts().size(), 1);
        Fact gedxFact = gedxRelationship.getFacts().get(0);
        assertNotNull(gedxFact);
        assertNotNull(gedxFact.getSources());
        assertEquals(gedxFact.getSources().size(), 1);
        SourceReference gedxSourceReference = gedxFact.getSources().get(0);
        assertNotNull(gedxSourceReference);
        assertNotNull(gedxSourceReference.getDescription());
        assertTrue(gedxSourceReference.getDescription().getResource().toString().startsWith("descriptions/"));
        generatedId = gedxSourceReference.getDescription().getResource().toString().substring("descriptions/".length());
        assertNotNull(generatedId, "2");
        assertNull(gedxSourceReference.getDescription().getExtensionAttributes());
        assertNull(gedxSourceReference.getDescription().getExtensionElements());
        assertNotNull(gedxSourceReference.getAttribution());
        assertEquals(gedxSourceReference.getAttribution().getKnownConfidenceLevel(), ConfidenceLevel.Possibly);
        assertNull(gedxSourceReference.getAttribution().getModified());
        assertNull(gedxSourceReference.getAttribution().getProofStatement());
        assertNull(gedxSourceReference.getAttribution().getContributor());
        assertNull(gedxSourceReference.getId());
        assertNull(gedxSourceReference.getType());
        assertNull(gedxSourceReference.getExtensionAttributes());
        assertNull(gedxSourceReference.getExtensionElements());
        assertNull(gedxSourceReference.getResource());
      }
    }
    assertNotNull(result.getDescriptions());
    assertEquals(result.getDescriptions().size(), 1);
    Description gedxSourceDescription = result.getDescriptions().get(0);
    assertNotNull(gedxSourceDescription);
    assertEquals(gedxSourceDescription.getId(), generatedId);
    assertNull(gedxSourceDescription.getType());
    assertNull(gedxSourceDescription.getExtensionAttributes());
    assertNull(gedxSourceDescription.getAbout());
    assertNotNull(gedxSourceDescription.getExtensionElements());
    DublinCoreDescriptionDecorator gedxDecoratedSourceDescription = DublinCoreDescriptionDecorator.newInstance(gedxSourceDescription);
    assertUnusedFieldsAreUnused(gedxDecoratedSourceDescription);
    assertEquals(gedxDecoratedSourceDescription.getCreated().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getDescription().size(), 1);
    assertEquals(gedxDecoratedSourceDescription.getDescription().get(0).getValue(), "\"Germany, Births and Baptisms, 1558-1898,\" index, FamilySearch (https://familysearch.org/pal:/MM9.1.1/VHQB-CHW :accessed 22 May 2012), Joannes Baptista Louwart.");
    assertEquals(gedxDecoratedSourceDescription.getIsPartOf().size(), 0);
  }

  @Test
  public void testToSourcesAndSourceReferences3() throws Exception {
    org.folg.gedcom.model.Person dqPerson = gedcom.getPeople().get(0);
    TestConversionResult result = new TestConversionResult();
    GedcomMapper gedcomMapper = new GedcomMapper();

    PersonMapper mapper = new PersonMapper();

    String generatedId = null;

    mapper.toPerson(dqPerson, result);
    assertNotNull(result.getPersons());
    assertEquals(result.getPersons().size(), 1);

    Person gedxPerson = result.getPersons().get(0);
    assertNotNull(gedxPerson.getFacts());
    assertEquals(gedxPerson.getFacts().size(), 1);
    Fact gedxFact = gedxPerson.getFacts().get(0);
    assertNotNull(gedxFact);
    assertNotNull(gedxFact.getSources());
    assertEquals(gedxFact.getSources().size(), 1);
    SourceReference gedxSourceReference = gedxFact.getSources().get(0);
    assertNotNull(gedxSourceReference);
    assertNotNull(gedxSourceReference.getDescription());
    assertTrue(gedxSourceReference.getDescription().getResource().toString().startsWith("descriptions/"));
    generatedId = gedxSourceReference.getDescription().getResource().toString().substring("descriptions/".length());
    assertNotNull(generatedId, "3");
    assertNull(gedxSourceReference.getDescription().getExtensionAttributes());
    assertNull(gedxSourceReference.getDescription().getExtensionElements());
    assertNotNull(gedxSourceReference.getAttribution());
    assertEquals(gedxSourceReference.getAttribution().getKnownConfidenceLevel(), ConfidenceLevel.Apparently);
    assertNull(gedxSourceReference.getAttribution().getModified());
    assertNull(gedxSourceReference.getAttribution().getProofStatement());
    assertNull(gedxSourceReference.getAttribution().getContributor());
    assertNull(gedxSourceReference.getId());
    assertNull(gedxSourceReference.getType());
    assertNull(gedxSourceReference.getExtensionAttributes());
    assertNull(gedxSourceReference.getExtensionElements());
    assertNull(gedxSourceReference.getResource());

    assertNotNull(result.getDescriptions());
    assertEquals(result.getDescriptions().size(), 1);
    Description gedxSourceDescription = result.getDescriptions().get(0);
    assertNotNull(gedxSourceDescription);
    assertEquals(gedxSourceDescription.getId(), generatedId);
    assertNull(gedxSourceDescription.getType());
    assertNull(gedxSourceDescription.getExtensionAttributes());
    assertNull(gedxSourceDescription.getAbout());
    assertNotNull(gedxSourceDescription.getExtensionElements());
    DublinCoreDescriptionDecorator gedxDecoratedSourceDescription = DublinCoreDescriptionDecorator.newInstance(gedxSourceDescription);
    assertUnusedFieldsAreUnused(gedxDecoratedSourceDescription);
    assertEquals(gedxDecoratedSourceDescription.getCreated().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getDescription().size(), 1);
    assertEquals(gedxDecoratedSourceDescription.getDescription().get(0).getValue(), "__SOUR_JoannesBaptistaLouwaert__");
    assertEquals(gedxDecoratedSourceDescription.getIsPartOf().size(), 0);
  }

  @Test
  public void testToSourcesAndSourceReferences4() throws Exception {
    org.folg.gedcom.model.Person dqPerson = gedcom.getPeople().get(1);
    TestConversionResult result = new TestConversionResult();
    GedcomMapper gedcomMapper = new GedcomMapper();

    PersonMapper mapper = new PersonMapper();

    String generatedId = null;

    mapper.toPerson(dqPerson, result);
    assertNotNull(result.getPersons());
    assertEquals(result.getPersons().size(), 1);

    Person gedxPerson = result.getPersons().get(0);
    assertNotNull(gedxPerson.getFacts());
    assertEquals(gedxPerson.getFacts().size(), 1);
    Fact gedxFact = gedxPerson.getFacts().get(0);
    assertNotNull(gedxFact);
    assertNotNull(gedxFact.getSources());
    assertEquals(gedxFact.getSources().size(), 1);
    SourceReference gedxSourceReference = gedxFact.getSources().get(0);
    assertNotNull(gedxSourceReference);
    assertNotNull(gedxSourceReference.getDescription());
    assertTrue(gedxSourceReference.getDescription().getResource().toString().startsWith("descriptions/"));
    generatedId = gedxSourceReference.getDescription().getResource().toString().substring("descriptions/".length());
    assertNotNull(generatedId, "4");
    assertNull(gedxSourceReference.getDescription().getExtensionAttributes());
    assertNull(gedxSourceReference.getDescription().getExtensionElements());
    assertNotNull(gedxSourceReference.getAttribution());
    assertEquals(gedxSourceReference.getAttribution().getKnownConfidenceLevel(), ConfidenceLevel.Perhaps);
    assertNull(gedxSourceReference.getAttribution().getModified());
    assertNull(gedxSourceReference.getAttribution().getProofStatement());
    assertNull(gedxSourceReference.getAttribution().getContributor());
    assertNull(gedxSourceReference.getId());
    assertNull(gedxSourceReference.getType());
    assertNull(gedxSourceReference.getExtensionAttributes());
    assertNull(gedxSourceReference.getExtensionElements());
    assertNull(gedxSourceReference.getResource());

    assertNotNull(result.getDescriptions());
    assertEquals(result.getDescriptions().size(), 0);
  }

  @Test
  public void testToSourcesAndSourceReferences5() throws Exception {
    org.folg.gedcom.model.Person dqPerson = gedcom.getPeople().get(2);
    TestConversionResult result = new TestConversionResult();
    GedcomMapper gedcomMapper = new GedcomMapper();

    PersonMapper mapper = new PersonMapper();

    String generatedId = null;

    mapper.toPerson(dqPerson, result);
    assertNotNull(result.getPersons());
    assertEquals(result.getPersons().size(), 1);

    Person gedxPerson = result.getPersons().get(0);
    assertNotNull(gedxPerson.getFacts());
    assertEquals(gedxPerson.getFacts().size(), 1);
    Fact gedxFact = gedxPerson.getFacts().get(0);
    assertNotNull(gedxFact);
    assertNull(gedxFact.getSources());
// TODO: will probably be needed when the TEXT tag starts being processed
//    assertEquals(gedxFact.getSources().size(), 1);
//    SourceReference gedxSourceReference = gedxFact.getSources().get(0);
//    assertNotNull(gedxSourceReference);
//    assertNotNull(gedxSourceReference.getDescription());
//    assertTrue(gedxSourceReference.getDescription().getResource().toString().startsWith("descriptions/"));
//    generatedId = gedxSourceReference.getDescription().getResource().toString().substring("descriptions/".length());
//    assertNotNull(generatedId, "5");
//    assertNull(gedxSourceReference.getDescription().getExtensionAttributes());
//    assertNull(gedxSourceReference.getDescription().getExtensionElements());
//    assertNull(gedxSourceReference.getAttribution());
//    assertNull(gedxSourceReference.getId());
//    assertNull(gedxSourceReference.getType());
//    assertNull(gedxSourceReference.getExtensionAttributes());
//    assertNull(gedxSourceReference.getExtensionElements());
//    assertNull(gedxSourceReference.getResource());

    assertNotNull(result.getDescriptions());
    assertEquals(result.getDescriptions().size(), 0);
  }

  private void assertUnusedFieldsAreUnused(DublinCoreDescriptionDecorator gedxDecoratedSourceDescription) {
    assertEquals(gedxDecoratedSourceDescription.getAbstract().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getAccessRights().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getAccrualMethod().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getAccrualPeriodicity().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getAccrualPolicy().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getAlternative().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getAudience().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getAvailable().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getBibliographicCitation().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getConformsTo().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getContributor().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getCoverage().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getCreator().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getDate().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getDateAccepted().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getDateCopyrighted().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getDateSubmitted().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getEducationLevel().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getExtent().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getFormat().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getHasFormat().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getHasPart().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getHasVersion().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getIdentifier().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getInstructionalMethod().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getIsFormatOf().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getIsReferencedBy().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getIsReplacedBy().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getIsRequiredBy().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getIssued().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getIsVersionOf().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getLanguage().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getLicense().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getMediator().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getMedium().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getModified().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getProvenance().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getPublisher().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getReferences().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getRelation().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getReplaces().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getRequires().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getRights().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getRightsHolder().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getSource().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getSpatial().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getSubject().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getTableOfContents().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getTemporal().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getTitle().size(), 0);
    assertEquals(gedxDecoratedSourceDescription.getValid().size(), 0);
  }
}
