
CREATE OR REPLACE VIEW ekiosk.view_user_roles AS 
 SELECT roles.role_name,
    user_profil.cardno
   FROM ekiosk.j_user_role,
    ekiosk.roles,
    ekiosk.user_profil
  WHERE j_user_role.rid = roles.id AND user_profil.id = j_user_role.uid;

CREATE OR REPLACE VIEW ekiosk.view_user AS 
 SELECT user_profil.cardno, char(50) 'b9e78e02a97b1fa8aa0a2dca67d04095' as password
   FROM ekiosk.user_profil;