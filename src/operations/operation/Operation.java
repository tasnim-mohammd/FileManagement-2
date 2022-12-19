package operations.operation;

import operations.delete.IDelete;
import operations.export.IExport;
import operations.importOperation.IImport;
import operations.read.IRead;
import operations.rollback.IRollback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Operation implements IOperation {
    private IDelete delete;
    private IExport export;
    private IImport importFile;
    private IRollback rollback;
    private IRead read;

    public Operation(){
        this.delete = null;
        this.export = null;
        this.importFile = null;
        this.rollback = null;
        this.read = null;
    }

    public void importFiles(Connection connection,String path) throws SQLException, IOException {
        importFile.importFile(connection, path);

    }
    public void deleteFiles(Connection connection) throws SQLException {
    delete.delete(connection);
    }

    public void exportFile(){

    }
    public void rollBack(Connection connection,String path) throws SQLException, IOException {
    rollback.rollbackVersion(connection,path);
    }
    public void createClassification(){

    }

    public IDelete getDelete() {
        return delete;
    }

    public void setDelete(IDelete delete) {
        this.delete = delete;

    }

    public IExport getExport() {
        return export;
    }

    public void setExport(IExport export) {
        this.export = export;
    }

    public IImport getImportFile() {
        return importFile;
    }

    public void setImportFile(IImport importFile) {
        this.importFile = importFile;
    }

    public IRollback getRollback() {
        return rollback;
    }

    public void setRollback(IRollback rollback) {
        this.rollback = rollback;
    }

    public IRead getRead() {
        return read;
    }

    public void setRead(IRead read) {
        this.read = read;
    }



}