package dz.gouv.mesrs.dao;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchMode {

	Integer pageSize;
	Integer startIndex;
	private List<SortField> sortFields;
	private List<Filter> filters;
	private String dozerMapId;

	private boolean disjunction;

	public SearchMode(Integer pageSize, Integer startIndex) {
		super();
		this.pageSize = pageSize;
		this.startIndex = startIndex;
	}

	public SearchMode() {
		super();
		pageSize = null;
		startIndex = null;
	}

	public void addSortField(SortField sortField) {
		getSortFields().add(sortField);
	}

	public List<SortField> getSortFields() {
		if (sortFields == null) {
			sortFields = new ArrayList<>();
		}
		return sortFields;
	}

	public List<Filter> getFilters() {
		if (filters == null) {
			filters = new ArrayList<>();
		}
		return filters;
	}

	public void addFilter(Filter filter) {
		getFilters().add(filter);
	}

	public void addFilterAnd(Filter... filters) {
		addFilter(Filter.and(filters));
	}

	public void addFilterOr(Filter... filters) {
		addFilter(Filter.or(filters));
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public String getDozerMapId() {
		return dozerMapId;
	}

	public void setDozerMapId(String dozerMapId) {
		this.dozerMapId = dozerMapId;
	}

	public boolean isDisjunction() {
		return disjunction;
	}

	public void setDisjunction(boolean disjunction) {
		this.disjunction = disjunction;
	}

	@Override
	public String toString() {
		return "SearchMode [pageSize=" + pageSize + ", startIndex=" + startIndex + ", sortFields=" + sortFields
				+ ", filters=" + filters + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filters == null) ? 0 : filters.hashCode());
		result = prime * result + ((pageSize == null) ? 0 : pageSize.hashCode());
		result = prime * result + ((sortFields == null) ? 0 : sortFields.hashCode());
		result = prime * result + ((startIndex == null) ? 0 : startIndex.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchMode other = (SearchMode) obj;
		if (filters == null) {
			if (other.filters != null)
				return false;
		} else if (!filters.equals(other.filters))
			return false;
		if (pageSize == null) {
			if (other.pageSize != null)
				return false;
		} else if (!pageSize.equals(other.pageSize))
			return false;
		if (sortFields == null) {
			if (other.sortFields != null)
				return false;
		} else if (!sortFields.equals(other.sortFields))
			return false;
		if (startIndex == null) {
			if (other.startIndex != null)
				return false;
		} else if (!startIndex.equals(other.startIndex))
			return false;
		return true;
	}

	public static class Filter {
		private final String fieldName;
		private Object value1;
		private Object value2;
		private Object[] values;
		private final FilterMode mode;
		private MatchMode matchMode;

		public Filter(String fieldName, Object value1, Object value2, FilterMode mode) {
			super();
			this.fieldName = fieldName;
			this.value1 = value1;
			this.value2 = value2;
			this.mode = mode;
		}

		public Filter(String fieldName, Object value1, FilterMode mode) {
			super();
			this.fieldName = fieldName;
			this.value1 = value1;
			this.mode = mode;
		}

		public Filter(String fieldName, Object value1, FilterMode mode, MatchMode matchMode) {
			super();
			this.fieldName = fieldName;
			this.value1 = value1;
			this.mode = mode;
			this.matchMode = matchMode;
		}

		public Filter(String fieldName, Object[] values, FilterMode mode) {
			super();
			this.value1 = null;
			this.fieldName = fieldName;
			this.values = values;
			this.mode = mode;
		}

		public Filter(String fieldName, FilterMode mode) {
			super();
			this.fieldName = fieldName;
			this.mode = mode;
		}

		public String getFieldName() {
			return fieldName;
		}

		public Object getValue1() {
			return value1;
		}

		public Object getValue2() {
			return value2;
		}

		public FilterMode getMode() {
			return mode;
		}

		public Object[] getValues() {
			return values;
		}

		public void add(Filter filter) {
			if (value1 == null || !(value1 instanceof List)) {
				value1 = new ArrayList();
			}
			((List) value1).add(filter);
		}

		public static Filter and(Filter... filters) {
			Filter filter = new Filter("AND", null, FilterMode.AND);
			for (Filter f : filters) {
				filter.add(f);
			}
			return filter;
		}

		/**
		 * Create a new Filter using the OR operator.
		 * 
		 * <p>
		 * This takes a variable number of parameters. Any number of
		 * <code>Filter</code>s can be specified.
		 */
		public static Filter or(Filter... filters) {
			Filter filter = new Filter("OR", null, FilterMode.OR);
			for (Filter f : filters) {
				filter.add(f);
			}
			return filter;
		}

		@Override
		public String toString() {
			return "Filter [fieldName=" + fieldName + ", value1=" + value1 + ", value2=" + value2 + ", values="
					+ Arrays.toString(values) + ", mode=" + mode + "]";
		}

		public MatchMode getMatchMode() {
			return matchMode;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
			result = prime * result + ((mode == null) ? 0 : mode.hashCode());
			result = prime * result + ((value1 == null) ? 0 : value1.hashCode());
			result = prime * result + ((value2 == null) ? 0 : value2.hashCode());
			result = prime * result + Arrays.hashCode(values);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Filter other = (Filter) obj;
			if (fieldName == null) {
				if (other.fieldName != null)
					return false;
			} else if (!fieldName.equals(other.fieldName))
				return false;
			if (mode != other.mode)
				return false;
			if (value1 == null) {
				if (other.value1 != null)
					return false;
			} else if (!value1.equals(other.value1))
				return false;
			if (value2 == null) {
				if (other.value2 != null)
					return false;
			} else if (!value2.equals(other.value2))
				return false;
			if (!Arrays.equals(values, other.values))
				return false;
			return true;
		}

	}

	public static class SortField {
		private final String fieldName;
		private final SortOrder order;

		public SortField(String filedName, SortOrder order) {
			super();
			this.fieldName = StringUtils.remove(filedName, "Dto");
			this.order = order;
		}

		public String getFieldName() {
			return fieldName;
		}

		public SortOrder getOrder() {
			return order;
		}

		@Override
		public String toString() {
			return "SortField [fieldName=" + fieldName + ", order=" + order + "]";
		}

	}

	public enum SortOrder {
		DESC, ASC
	}

	public enum FilterMode {
		LESS, LESS_OR_EQUAL, GREATER, GREATER_OR_EQUAL, BETWEEN, IS_NULL, NOT, IN, NOT_IN, EQUALS, IS_NOT_NULL, LIKE, AND, OR
	}

	public enum MatchMode {
		EXACT, START, END, ANYWHERE
	}

}
