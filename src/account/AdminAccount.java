package account;

import java.io.Serializable;
import java.util.ArrayList;

public class AdminAccount implements Serializable {
    private String adminAccount;
    private String adminPassword;
    private final ArrayList<AdminAccount> listAdminAccounts = new ArrayList<>();

    public AdminAccount(String adminAccount, String adminPassword) {
        this.adminAccount = adminAccount;
        this.adminPassword = adminPassword;
    }

    public AdminAccount() {
        listAdminAccounts.add(new AdminAccount("admin", "admin"));
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public ArrayList<AdminAccount> getListAdminAccounts() {
        return listAdminAccounts;
    }
}


