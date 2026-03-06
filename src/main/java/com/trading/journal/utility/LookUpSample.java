package com.trading.journal.utility;

public class LookUpSample {
    /*
    @PostMapping (value = "/user-admin/$search")
    public List<LookUpData> getLookUpData(@Validated @RequestBody LookUpRequest lookUpRequest) {
        lookUpRequestValidator.validateLookUpRequest(lookUpRequest);
        return lookUpService.getLookUpData(lookUpRequest);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public class LookUpRequest {

        @Schema (description = "requestType", requiredMode = Schema.RequiredMode.REQUIRED)
        String requestType;

        @Schema (description = "accessControlId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @JsonProperty ("accessControlId")
        String accessControlId;

        @Schema (description = "filters")
        List<String> filters;
    }

    @Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LookUpData {
@Schema (description = "fieldName")
String fieldName;
@Schema (description = "list of values")
List<String> values;
}
//next
    public List<LookUpData> getLookUpData (LookUpRequest lookUpRequest) {
        Map<String, Set<String>> filterMap = new HashMap<>();
        lookUpRequest.getFilters().forEach(filter -> filterMap.put(filter, new TreeSet<>(String.CASE_INSENSITIVE_ORDER)));
        try {
            switch (lookUpRequest.getRequestType()) {
                case LOOKUP_USER -> buildUserLookUp(lookUpRequest, filterMap);
                case LOOKUP_ROLE -> buildRoleLookUp(lookUpRequest, filterMap);
                case LOOKUP_PERMISSION -> buildPermissionLookUp(lookUpRequest, filterMap);
                case LOOKUP_USER_AUDIT -> buildUserAuditLookUp(lookUpRequest, filterMap);
                case LOOK_UP_BULK_LOAD_SUMMARY -> buildbulkLoadSummaryLookUp(lookUpRequest, filterMap);
            }

            return filterMap.entrySet().stream().map(data -> LookUpData.builder().fieldName(data.getKey()).values(new ArrayList<>(data.getValue())).build()).toList();

        } catch (Exception e) {
            log.error("Exception in getLookUpDetails " + e.getLocalizedMessage());
            throw new InternalServerException(e.getLocalizedMessage());
        }
    }
    private void buildbulkLoadSummaryLookUp (LookUpRequest lookUpRequest, Map<String, Set<String>> filterMap) {
//List<AccessControlAudit> accessControlAuditList = accessControlAuditRepository.findAll();
        List<BulkUploadDetails> bulkLoadSummaryList = bulkLoadDetailsRepository.findAll();
        bulkLoadSummaryList.forEach(summary -> {
            addLookupField(lookUpRequest, filterMap, FILE_ID, String.valueOf(summary.getFileId()));
            addLookupField(lookUpRequest, filterMap, FILE_NAME, summary.getFileName());
            addLookupField(lookUpRequest, filterMap, CREATOR_ID, summary.getCreator().getDisplayName());
            addLookupField(lookUpRequest, filterMap, FILE_CREATED_TMS, summary.getCreatedTms().toString());
            addLookupField(lookUpRequest, filterMap, FILE_STATUS, String.valueOf(summary.getStatusCode()));
            addLookupField(lookUpRequest, filterMap, CREATOR_NAME, getSummaryDisplayName(summary, CREATOR_NAME));
        });
    }
    private void buildbulkLoadSummaryLookUp (LookUpRequest lookUpRequest, Map<String, Set<String>> filterMap) {
//List<AccessControlAudit> accessControlAuditList = accessControlAuditRepository.findAll();
        List<BulkUploadDetails> bulkLoadSummaryList = bulkLoadDetailsRepository.findAll();
        bulkLoadSummaryList.forEach(summary -> {
            addLookupField(lookUpRequest, filterMap, FILE_ID, String.valueOf(summary.getFileId()));
            addLookupField(lookUpRequest, filterMap, FILE_NAME, summary.getFileName());
            addLookupField(lookUpRequest, filterMap, CREATOR_ID, summary.getCreator().getDisplayName());
            addLookupField(lookUpRequest, filterMap, FILE_CREATED_TMS, summary.getCreatedTms().toString());
            addLookupField(lookUpRequest, filterMap, FILE_STATUS, String.valueOf(summary.getStatusCode()));
            addLookupField(lookUpRequest, filterMap, CREATOR_NAME, getSummaryDisplayName(summary, CREATOR_NAME));
        });
    }

    private void addLookupField (LookUpRequest lookUpRequest, Map<String, Set<String>> filterMap, String fieldName, String fieldValue) {
        if (validateLookUpData(lookUpRequest, fieldName, fieldValue)) {
            if(ROLE_DESC.equalsIgnoreCase(fieldName)){
                boolean exists = filterMap.get(fieldName).stream().anyMatch(s -> s.equalsIgnoreCase(fieldValue));
                if (!exists) {
                    filterMap.get(fieldName).add(fieldValue);
                }
            }else{
                filterMap.get(fieldName).add(fieldValue);
            }
        }
    }

    private boolean validateLookUpData (LookUpRequest lookUpRequest, String lookUpFieldName, String lookUpFieldValue) {
        return (lookUpRequest.getFilters().contains(lookUpFieldName) && StringUtils.isNotBlank(lookUpFieldValue));
    }*/
}
