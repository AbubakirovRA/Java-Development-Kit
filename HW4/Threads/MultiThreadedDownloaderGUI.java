import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.io.File;

public class MultiThreadedDownloaderGUI {
    private JFrame frame;
    private JTextField fileUrlField;
    private JTextField saveDirectoryField;
    private JTextField numberOfThreadsField;
    private JButton downloadButton;

    public MultiThreadedDownloaderGUI() {
        // Создаем графический интерфейс
        frame = new JFrame("MultiThreaded Downloader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(5, 2));

        JLabel fileUrlLabel = new JLabel("File URL: ");
        fileUrlField = new JTextField();
        JLabel saveDirectoryLabel = new JLabel("Save Directory: ");
        saveDirectoryField = new JTextField();
        JLabel numberOfThreadsLabel = new JLabel("Number of Threads: ");
        numberOfThreadsField = new JTextField();
        downloadButton = new JButton("Download");

        frame.add(fileUrlLabel);
        frame.add(fileUrlField);
        frame.add(saveDirectoryLabel);
        frame.add(saveDirectoryField);
        frame.add(numberOfThreadsLabel);
        frame.add(numberOfThreadsField);
        frame.add(new JPanel()); // Placeholder
        frame.add(downloadButton);

        // Назначаем слушателя события нажатия на кнопку "Download"
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadFile();
            }
        });

        frame.setVisible(true);
    }

    public void downloadFile() {
        // Отключаем кнопку загрузки, чтобы избежать повторных нажатий
        downloadButton.setEnabled(false);
        String fileUrl = fileUrlField.getText();
        String saveDirectory = saveDirectoryField.getText();
        int numberOfThreads = Integer.parseInt(numberOfThreadsField.getText());

        // Создаем пул потоков для загрузки файла
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        try {
            // Создаем URL из введенного URL-адреса
            URL url = new URL(fileUrl);
            // Открываем поток для чтения данных из URL-адреса
            InputStream inputStream = url.openStream();

            // Получаем имя файла из URL-адреса
            String fileName = getFileNameFromUrl(fileUrl);
            // Составляем полный путь для сохранения файла
            String savePath = saveDirectory + File.separator + fileName;

            // Создаем поток для записи данных в файл
            FileOutputStream outputStream = new FileOutputStream(savePath);

            byte[] buffer = new byte[4096];
            int bytesRead;

            // Читаем данные из URL-адреса и передаем их в потоки для записи
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                executor.execute(new DownloadTask(buffer, bytesRead, outputStream));
            }

            // Завершаем работу пула потоков
            executor.shutdown();
            // Ожидаем завершения всех задач
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

            // Открываем папку, где был сохранен файл
            openDownloadedFolder(savePath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "File download error");
        }

        // Включаем кнопку загрузки после завершения
        downloadButton.setEnabled(true);
    }

    public void openDownloadedFolder(String filePath) {
        try {
            // Открываем папку, содержащую скачанный файл
            Desktop.getDesktop().open(new File(filePath).getParentFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileNameFromUrl(String fileUrl) {
        // Получаем имя файла из URL-адреса
        int slashIndex = fileUrl.lastIndexOf('/');
        if (slashIndex != -1 && slashIndex < fileUrl.length() - 1) {
            return fileUrl.substring(slashIndex + 1);
        }
        // Если имя файла не найдено, возвращаем "downloaded-file"
        return "downloaded-file";
    }

    public static void main(String[] args) {
        // Запускаем графический интерфейс в потоке Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MultiThreadedDownloaderGUI();
            }
        });
    }

    // Задача для скачивания данных и записи в файл
    static class DownloadTask implements Runnable {
        private byte[] buffer;
        private int bytesRead;
        private FileOutputStream outputStream;

        public DownloadTask(byte[] buffer, int bytesRead, FileOutputStream outputStream) {
            this.buffer = buffer;
            this.bytesRead = bytesRead;
            this.outputStream = outputStream;
        }

        @Override
        public void run() {
            try {
                // Записываем данные в файл
                outputStream.write(buffer, 0, bytesRead);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
