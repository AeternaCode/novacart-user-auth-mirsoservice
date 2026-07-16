package com.test.userauthservice.specifications.Users;

import com.test.userauthservice.entity.Users;
import com.test.userauthservice.utils.ENUMS.UserStatus;
import org.springframework.data.jpa.domain.Specification;

public final class UserSpecification {

    private UserSpecification() {}

    /** Insted of below ge use lambda expression and return directly.
     * Specification<Product> sep = new Specification<Product>() {
     *             public @Nullable Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
     *                 return null;
     *             }
     *         };
     *         or
     *         Specification<Product> spec = (root, query, criteriaBuilder) ->
     *                 criteriaBuilder.isNull(root.get("deletedAt"));
     * */
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String STATUS = "status";
    private static final String ROLE = "role";
    private static final String ID = "id";
    private static final String EMAIL_VERIFIED = "emailVerified";
    private static final String PHONE_VERIFIED = "phoneVerified";
    private static final String DELETED_AT = "deletedAt";

     public static Specification<Users> isNotDeleted(){
         return ((root, query, criteriaBuilder) ->
                 criteriaBuilder.isNull(root.get(DELETED_AT))
                 );
     }
     public static Specification<Users> isDeleted(){
         return ((root, query, criteriaBuilder) ->
                  criteriaBuilder.isNotNull(root.get(DELETED_AT))
                  );
     }
     public static Specification<Users> hasKeyword(String keyword){
         String searchKeyword = "%" + keyword.toLowerCase() + "%";
         return ((root, query, criteriaBuilder) ->
                 criteriaBuilder.or(
                         criteriaBuilder.like(criteriaBuilder.lower(root.get(FIRST_NAME)), searchKeyword),
                         criteriaBuilder.like(criteriaBuilder.lower(root.get(LAST_NAME)), searchKeyword),
                         criteriaBuilder.like(criteriaBuilder.lower(root.get(EMAIL)), searchKeyword)
                 )
         );
    }
     public static Specification<Users> hasPhoneNumber(String phoneNumber){
         return ((root, query, criteriaBuilder) ->
                  criteriaBuilder.like(root.get(PHONE_NUMBER), "%" + phoneNumber + "%")
                  );
     }
     public static Specification<Users> hasStatus(UserStatus status){
         return ((root, query, criteriaBuilder) ->
                 criteriaBuilder.equal(root.get(STATUS), status)
                 );
     }
     public static Specification<Users> hasRole(Long roleId){
         return ((root, query, criteriaBuilder) ->
                 criteriaBuilder.equal(root.get(ROLE).get(ID), roleId)
                 );
     }
     public static Specification<Users> hasEmailVerified(Boolean emailVerified){
         return ((root, query, criteriaBuilder) ->
                 criteriaBuilder.equal(root.get(EMAIL_VERIFIED), emailVerified)
                 );
     }
     public static Specification<Users> hasPhoneVerified(Boolean phoneVerified){
         return ((root, query, criteriaBuilder) ->
                 criteriaBuilder.equal(root.get(PHONE_VERIFIED), phoneVerified)
                 );
     }
}