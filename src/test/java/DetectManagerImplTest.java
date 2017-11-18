import org.junit.Before;

import com.example.detector.DetectManagerImpl;
import com.example.model.TextImage;

/**
 *
 */
public class DetectManagerImplTest {
	private final static int NO_MATCH = 0;

	DetectManagerImpl searchManager;
	TextImage image;
	TextImage pattern1;
	TextImage pattern2;

	@Before
	public void setUp() throws Exception {
		image = new TextImage("image.txt");
		pattern1 = new TextImage("pattern1.txt");
		pattern2 = new TextImage("pattern2.txt");
		searchManager = new DetectManagerImpl();
	}

//	@Test
//	public void convert() throws Exception {
//		final TextImage subsection1 = pattern1.getSubsection(0, 0, 4, 4);
//		final TextImage subsection2 = pattern2.getSubsection(0, 0, 4, 4);
//		System.out.println(subsection1.getImage());
//		System.out.println(subsection2.getImage());
//		final int confidenceValue = searchManager.calculateMatchCount(subsection1, subsection2);
//		Assert.assertEquals(18, confidenceValue);
//	}
//
//
//	@Test
//	public void getInvalidConfidenceValue2() throws Exception {
//		final TextImage subsection1 = pattern1.getSubsection(0, 0, 5, 4);
//		final TextImage subsection2 = pattern2.getSubsection(0, 0, 4, 4);
//		System.out.println(subsection1.getImage());
//		System.out.println(subsection2.getImage());
//		final int confidenceValue = searchManager.calculateMatchCount(subsection1, subsection2);
//		Assert.assertEquals(0, confidenceValue);
//	}

}