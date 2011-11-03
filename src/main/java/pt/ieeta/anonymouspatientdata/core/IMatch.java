/*  Copyright   2011 - Luís A. Bastião Silva
 *
 *  This file is part of AnonymousPatientData.
 *
 *  AnonymousPatientData is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  AnonymousPatientData is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with PACScloud.  If not, see <http://www.gnu.org/licenses/>.
 */


package pt.ieeta.anonymouspatientdata.core;

/**
 *
 * @author Luís A. Bastião Silva <bastiao@ua.pt>
 */
public interface IMatch 
{
    public String getName(String word);
    public String getPatientID(String word);
    public String getAccessionNumber(String word);
    
}
