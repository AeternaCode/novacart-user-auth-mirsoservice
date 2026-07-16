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

     public static Specification<Users> isNotDeleted(){
         return ((root, query, criteriaBuilder) ->
                 criteriaBuilder.isNull(root.get("deletedAt"))
                 );
     }
     public static Specification<Users> isDeleted(){
         return ((root, query, criteriaBuilder) ->
                  criteriaBuilder.isNotNull(root.get("deletedAt"))
                  );
     }
     public static Specification<Users> hasKeyword(String keyword){
         String searchKeyword = "%" + keyword.toLowerCase() + "%";
         return ((root, query, criteriaBuilder) ->
                 criteriaBuilder.or(
                         criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), searchKeyword),
                         criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), searchKeyword),
                         criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), searchKeyword)
                 )
         );
    }
     public static Specification<Users> hasPhoneNumber(String phoneNumber){
         return ((root, query, criteriaBuilder) ->
                  criteriaBuilder.like(root.get("phoneNumber"), "%" + phoneNumber + "%")
                  );
     }
     public static Specification<Users> hasStatus(UserStatus status){
         return ((root, query, criteriaBuilder) ->
                 criteriaBuilder.equal(root.get("status"), status)
                 );
     }
     public static Specification<Users> hasRole(Long roleId){
         return ((root, query, criteriaBuilder) ->
                 criteriaBuilder.equal(root.get("role").get("id"), roleId)
                 );
     }
     public static Specification<Users> hasEmailVerified(Boolean emailVerified){
         return ((root, query, criteriaBuilder) ->
                 criteriaBuilder.equal(root.get("emailVerified"), emailVerified)
                 );
     }
     public static Specification<Users> hasPhoneVerified(Boolean phoneVerified){
         return ((root, query, criteriaBuilder) ->
                 criteriaBuilder.equal(root.get("phoneVerified"), phoneVerified)
                 );
     }
}