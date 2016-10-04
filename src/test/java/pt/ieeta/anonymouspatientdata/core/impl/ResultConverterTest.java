/*  Copyright   2016 - Jorge Miguel Ferreira da Silva
 *
 *  This file is part of AnonymousPatientData.
 *
 *  ResultConverterTest is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  ResultConverterTest is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with PACScloud.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.ieeta.anonymouspatientdata.core.impl;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import pt.ua.dicoogle.sdk.datastructs.SearchResult;

/**
 * @author Jorge Miguel Ferreira da Silva
 *
 */
public class ResultConverterTest {
	Map<String,PatientData> patientDataMap;
	Map<String, String> studyDataMap;
	HashMap<String,Object> data;
	PatientData Pd;
	URI location;
	@Mock
	AnonDatabase Anon;

	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before 
	public void create()throws IOException{
		Anon =Mockito.mock(AnonDatabase.class);

		when(Anon.getmapAccessionNumber(anyString())).thenReturn("321");
		when(Anon.getAccessionNumberByAccessionMapNumber(anyString())).thenReturn(studyDataMap.get("2"));		
		when(Anon.getPatientIdByPatientMapId(anyString())).thenReturn(patientDataMap.get("123").getPatientId());	
		when(Anon.getPatientNameByPatientMapId(anyString())).thenReturn(patientDataMap.get("123").getPatientName());		
		when(Anon.getAccessionNumberByAccessionMapNumber(anyString())).thenReturn(studyDataMap.get(anyString()));
		when(Anon.getmapIdbyPatientId(patientDataMap.get("123").getPatientId())).thenReturn(patientDataMap.get("123").getMapId());
		when(Anon.getmapIdbyPatientName(anyString())).thenReturn("123");
	}

	@Before
	public void setUp() throws Exception {
		patientDataMap= new HashMap<String, PatientData>();
		studyDataMap=new HashMap<String, String>() ;

		location= new URI("test");
		data= new HashMap<String,Object>();
		data.put("PatientName", "123");
		data.put("PatientId", "123");
		data.put("AccessionNumber", "321");
		Integer id=1;
		String patientId = id.toString();
		Integer aN=1*2;
		String accessionNumber = aN.toString();
		String patientName ="a" +  patientId;
		String patientMapId = "123";
		String accessionMapNumber="321";
		PatientData Pd=new PatientData(patientName, patientId,patientMapId);
		patientDataMap.put(Pd.getMapId(), Pd);
		studyDataMap.put(accessionMapNumber,accessionNumber);
	}


	@Test
	public void test() throws IOException {
		
		ResultConverter rC =new ResultConverter(Anon);
		
		SearchResult rs=new SearchResult(location, 0, data );
		
		SearchResult rs1 = rC.transform(rs);
		System.out.println(rs1.getScore());
		data.clear();
		//test
		data.put("PatientName", "a1");
		data.put("PatientId", "1");
		data.put("AccessionNumber", "2");
		SearchResult test=new SearchResult(location, 0.0, data );
		
		
		Assert.assertEquals(test.get("PatientName"),rs1.get("PatientName"));
		Assert.assertEquals(test.get("PatientId"),rs1.get("PatientId"));
		Assert.assertEquals(test.get("AccessionNumber"),rs1.get("AccessionNumber"));
		Assert.assertEquals(test.getURI(),rs1.getURI());
		Assert.assertEquals(test.getScore(),rs1.getScore(),0.001);
	}













}