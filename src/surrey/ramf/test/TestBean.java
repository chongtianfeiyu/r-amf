package surrey.ramf.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
/*
Copyright (c) 2014 Surrey Hughes

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

public class TestBean {

	private int anInt = 44800;
	private Integer anInteger = new Integer(45000);
	private long aLong = 432442l;
	private Long aLongObj = new Long(34534534l);
	private String aString = "this is a test string!!!0!";
	private Date aDate = new Date();
	private double aDouble = 23523.234234f;
	private Double aDoubleObj = new Double(4353.3434f);
	private List<Integer> intList = new ArrayList<Integer>();
	private List<String> stringList = new ArrayList<String>();

	public TestBean() {
		Random rand = new Random();

		for (int i = 0; i < 10; i++) {
			intList.add(new Integer(rand.nextInt()));
			stringList.add("This should be stored");
		}
	}

	/**
	 * @return the anInt
	 */
	public int getAnInt() {
		return anInt;
	}

	/**
	 * @param anInt
	 *            the anInt to set
	 */
	public void setAnInt(int anInt) {
		this.anInt = anInt;
	}

	/**
	 * @return the anInteger
	 */
	public Integer getAnInteger() {
		return anInteger;
	}

	/**
	 * @param anInteger
	 *            the anInteger to set
	 */
	public void setAnInteger(Integer anInteger) {
		this.anInteger = anInteger;
	}

	/**
	 * @return the aLong
	 */
	public long getaLong() {
		return aLong;
	}

	/**
	 * @param aLong
	 *            the aLong to set
	 */
	public void setaLong(long aLong) {
		this.aLong = aLong;
	}

	/**
	 * @return the aLongObj
	 */
	public Long getaLongObj() {
		return aLongObj;
	}

	/**
	 * @param aLongObj
	 *            the aLongObj to set
	 */
	public void setaLongObj(Long aLongObj) {
		this.aLongObj = aLongObj;
	}

	/**
	 * @return the aString
	 */
	public String getaString() {
		return aString;
	}

	/**
	 * @param aString
	 *            the aString to set
	 */
	public void setaString(String aString) {
		this.aString = aString;
	}

	/**
	 * @return the aDate
	 */
	public Date getaDate() {
		return aDate;
	}

	/**
	 * @param aDate
	 *            the aDate to set
	 */
	public void setaDate(Date aDate) {
		this.aDate = aDate;
	}

	/**
	 * @return the intList
	 */
	public List<Integer> getIntList() {
		return intList;
	}

	/**
	 * @param intList
	 *            the intList to set
	 */
	public void setIntList(List<Integer> intList) {
		this.intList = intList;
	}

	/**
	 * @return the stringList
	 */
	public List<String> getStringList() {
		return stringList;
	}

	/**
	 * @param stringList
	 *            the stringList to set
	 */
	public void setStringList(List<String> stringList) {
		this.stringList = stringList;
	}

	/**
	 * @return the aDouble
	 */
	public double getaDouble() {
		return aDouble;
	}

	/**
	 * @param aDouble
	 *            the aDouble to set
	 */
	public void setaDouble(double aDouble) {
		this.aDouble = aDouble;
	}

	/**
	 * @return the aDoubleObj
	 */
	public Double getaDoubleObj() {
		return aDoubleObj;
	}

	/**
	 * @param aDoubleObj
	 *            the aDoubleObj to set
	 */
	public void setaDoubleObj(Double aDoubleObj) {
		this.aDoubleObj = aDoubleObj;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TestBean [anInt=" + anInt + ", anInteger=" + anInteger + ", aLong=" + aLong + ", aLongObj=" + aLongObj
				+ ", aString=" + aString + ", aDate=" + aDate + ", aDouble=" + aDouble + ", aDoubleObj=" + aDoubleObj
				+ ", intList=" + intList + ", stringList=" + stringList + "]";
	}
}
