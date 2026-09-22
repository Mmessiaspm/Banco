package br.edu.ifpi.DAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    // A fábrica de EntityManagers. Inicialização preguiçosa para evitar
    // ExceptionInInitializerError quando o provider/config não estiver disponível.
    private static volatile EntityManagerFactory factory;

    private static EntityManagerFactory getFactory() {
        if (factory == null) {
            synchronized (JPAUtil.class) {
                if (factory == null) {
                    try {
                        factory = Persistence.createEntityManagerFactory("minhaUnidadeDePersistencia");
                    } catch (Exception ex) {
                        String msg = "Falha ao construir EntityManagerFactory. Verifique se 'META-INF/persistence.xml' está no classpath e se o persistence-unit 'minhaUnidadeDePersistencia' existe. Causa: " + ex.getMessage();
                        throw new IllegalStateException(msg, ex);
                    }
                }
            }
        }
        return factory;
    }

    // Método para obter uma instância de EntityManager
    public static EntityManager getEntityManager() {
        return getFactory().createEntityManager();
    }

    // Fecha a fábrica ao terminar a aplicação
    public static void close() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
