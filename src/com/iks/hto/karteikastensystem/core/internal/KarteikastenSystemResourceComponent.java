/**
 * <copyright>
 *
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ModelResourceComponent.java,v 1.2 2009/06/01 17:19:28 tschindl Exp $
 */
package com.iks.hto.karteikastensystem.core.internal;

import java.util.ArrayList;
import java.util.List;

import com.iks.hto.karteikastensystem.core.IKarteikastenSystemLoadingService;
import com.iks.hto.karteikastensystem.core.IKarteikastenSystemResource;
import com.iks.hto.karteikastensystem.core.IKarteikastenSystemResourceProvider;

public class KarteikastenSystemResourceComponent implements IKarteikastenSystemLoadingService {
	private List<IKarteikastenSystemResourceProvider> providers = new ArrayList<IKarteikastenSystemResourceProvider>();

	public void addProvider(IKarteikastenSystemResourceProvider provider) {
		synchronized (providers) {
			providers.add(provider);
		}
		System.out.println("KarteikastenSystemResourceComponent -> provider added: " + provider);
	}

	public void removeProvider(IKarteikastenSystemResourceProvider provider) {
		synchronized (providers) {
			providers.remove(provider);
		}
		System.out.println("KarteikastenSystemResourceComponent -> provider removed: " + provider);
	}

	public IKarteikastenSystemResource findAndLoadResource(String uri) {
		synchronized (providers) {
			for (IKarteikastenSystemResourceProvider p : providers) {
				if (p.canHandleUri(uri)) {
					return p.loadResource(uri);
				}
			}
		}

		return null;
	}
}
