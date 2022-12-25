package readDB;

import encryption.EncryptionFile;
import encryption.IEncryptionAndDecryption;
import exceptions.SqlQueryException;
import variables.Variables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FilesClassificationMatching {
    public static ResultSet getFiles(Connection connection, String[]context) throws SqlQueryException {
        IEncryptionAndDecryption EncryptionFile = new EncryptionFile();
        ResultSet result = null;
        String nameQuery = Variables.EQUALS;
        String typeQuery = Variables.EQUALS;
        String sizeQuery = Variables.EQUALS;
        if (context[0].equals(Variables.NO_Condition)) {
            nameQuery = Variables.NOT_EQUALS;
        }
        if (context[1].equals(Variables.NO_Condition)) {
            typeQuery = Variables.NOT_EQUALS;
        }
        if (context[2].equals(Variables.NO_Condition)) {
            sizeQuery = Variables.NOT_EQUALS;
        }
        try {
            String query = "SELECT * FROM file WHERE name" + nameQuery + "? AND type" + typeQuery + "? " +
                    "AND size" + sizeQuery + "?" + " AND lastVersion = 1";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, EncryptionFile.encryptAndDecrypt(context[0]));
            preparedStmt.setString(2, context[1]);
            preparedStmt.setString(3, context[2]);
            result = preparedStmt.executeQuery();
        } catch (SQLException e) {
            throw new SqlQueryException("find files query filed");
        }
        return result;
    }
}
