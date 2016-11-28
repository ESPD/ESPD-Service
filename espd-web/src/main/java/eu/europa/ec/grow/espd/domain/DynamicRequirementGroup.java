/*
 *
 * Copyright 2016 EUROPEAN COMMISSION
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/community/eupl/og_page/eupl
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 *
 */

package eu.europa.ec.grow.espd.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Container class for storing the values of requirement groups in a dynamic way, preventing a possible proliferation
 * of classes and fields. This class is mostly useful for modeling the unbounded requirement groups.
 *
 * Created by ratoico on 11/25/16.
 */
public class DynamicRequirementGroup implements Map<String, Object> {

	private final Map<String, Object> values;

	public DynamicRequirementGroup() {
		this(new HashMap<String, Object>(5));
	}

	DynamicRequirementGroup(Map<String, Object> values) {
		this.values = values;
	}

	@Override
	public int size() {
		return values.size();
	}

	@Override
	public boolean isEmpty() {
		return values.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return values.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return values.containsValue(value);
	}

	@Override
	public Object get(Object key) {
		return values.get(key);
	}

	@Override
	public Object put(String key, Object value) {
		return values.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return values.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ?> m) {
		values.putAll(m);
	}

	@Override
	public void clear() {
		values.clear();
	}

	@Override
	public Set<String> keySet() {
		return values.keySet();
	}

	@Override
	public Collection<Object> values() {
		return values.values();
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		return values.entrySet();
	}

}
